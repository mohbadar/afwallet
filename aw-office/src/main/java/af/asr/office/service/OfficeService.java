
package af.asr.office.service;

import af.asr.infrastructure.service.UserService;
import af.asr.office.ServiceConstants;
import af.asr.office.domain.Address;
import af.asr.office.domain.ExternalReference;
import af.asr.office.domain.Office;
import af.asr.office.domain.OfficePage;
import af.asr.office.mapper.AddressMapper;
import af.asr.office.mapper.OfficeMapper;
import af.asr.office.model.AddressEntity;
import af.asr.office.model.ExternalReferenceEntity;
import af.asr.office.model.OfficeEntity;
import af.asr.office.repository.AddressRepository;
import af.asr.office.repository.EmployeeRepository;
import af.asr.office.repository.ExternalReferenceRepository;
import af.asr.office.repository.OfficeRepository;
import af.asr.office.util.Utils;
import af.gov.anar.lang.infrastructure.exception.service.ServiceException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OfficeService {

  private final Logger logger;
  private final OfficeRepository officeRepository;
  private final AddressRepository addressRepository;
  private final EmployeeRepository employeeRepository;
  private final ExternalReferenceRepository externalReferenceRepository;
  private final UserService userService;

  @Autowired
  public OfficeService(@Qualifier(ServiceConstants.SERVICE_LOGGER_NAME) final Logger logger,
                       final OfficeRepository officeRepository,
                       final AddressRepository addressRepository,
                       final EmployeeRepository employeeRepository,
                       final ExternalReferenceRepository externalReferenceRepository, UserService userService) {
    super();
    this.logger = logger;
    this.officeRepository = officeRepository;
    this.addressRepository = addressRepository;
    this.employeeRepository = employeeRepository;
    this.externalReferenceRepository = externalReferenceRepository;
    this.userService = userService;
  }

  public boolean officeExists(final String identifier) {
    return this.officeRepository.existsByIdentifier(identifier);
  }

  public boolean branchExists(final String identifier) {
    final Optional<OfficeEntity> officeEntityOptional = this.officeRepository.findByIdentifier(identifier);
    return officeEntityOptional.map(officeEntity -> this.officeRepository.existsByParentOfficeId(officeEntity.getId())).orElse(false);
  }

  public boolean hasEmployees(final String officeIdentifier){
    return this.officeRepository.findByIdentifier(officeIdentifier)
            .map(this.employeeRepository::existsByAssignedOffice)
            .orElse(false);
  }

  @Transactional(readOnly = true)
  public OfficePage fetchOffices(final String term, final Pageable pageRequest) {
    final Page<OfficeEntity> officeEntityPage;
    if (term != null) {
      officeEntityPage = this.officeRepository.findByIdentifierContainingOrNameContaining(term, term, pageRequest);
    } else {
      officeEntityPage = this.officeRepository.findByParentOfficeIdIsNull(pageRequest);
    }

    final OfficePage officePage = new OfficePage();
    officePage.setTotalPages(officeEntityPage.getTotalPages());
    officePage.setTotalElements(officeEntityPage.getTotalElements());
    officePage.setOffices(this.extractOfficeEntities(officeEntityPage, null));

    return officePage;
  }

  public Optional<Office> findOfficeByIdentifier(final String identifier) {
    final Optional<OfficeEntity> officeEntityOptional = this.officeRepository.findByIdentifier(identifier);

    if (officeEntityOptional.isPresent()) {
      final Optional<Office> officeOptional = officeEntityOptional.map(OfficeMapper::map);

      officeOptional.ifPresent(office -> {
        final Long parentOfficeId = officeEntityOptional.get().getParentOfficeId();
        if(parentOfficeId != null) {
          final OfficeEntity parentEntity = this.officeRepository.getOne(parentOfficeId);
          office.setParentIdentifier(parentEntity.getIdentifier());
        }

        final Optional<AddressEntity> addressEntityOptional = this.addressRepository.findByOffice(officeEntityOptional.get());
        addressEntityOptional.ifPresent(addressEntity -> office.setAddress(AddressMapper.map(addressEntity)));

        office.setExternalReferences(
            this.branchExists(office.getIdentifier())
                || this.hasEmployees(office.getIdentifier())
                || this.hasExternalReferences(office.getIdentifier())
        );
      });

      return officeOptional;
    }
    return Optional.empty();
  }

  public Optional<Address> findAddressOfOffice(final String identifier) {
    final Optional<OfficeEntity> officeEntityOptional = this.officeRepository.findByIdentifier(identifier);

    if (!officeEntityOptional.isPresent()) {
      throw ServiceException.notFound("Office {0} not found.", identifier);
    }

    final Optional<AddressEntity> addressEntityOptional = this.addressRepository.findByOffice(officeEntityOptional.get());

    return addressEntityOptional.map(AddressMapper::map);
  }

  @Transactional(readOnly = true)
  public OfficePage fetchBranches(final String parentIdentifier, final Pageable pageRequest) {
    final OfficeEntity parentOfficeEntity = this.officeRepository.findByIdentifier(parentIdentifier)
        .orElseThrow(() -> ServiceException.notFound("Parent office {0} not found!", parentIdentifier));

    final Page<OfficeEntity> officeEntityPage = this.officeRepository.findByParentOfficeId(parentOfficeEntity.getId(), pageRequest);
    final OfficePage officePage = new OfficePage();
    officePage.setTotalPages(officeEntityPage.getTotalPages());
    officePage.setTotalElements(officeEntityPage.getTotalElements());
    officePage.setOffices(this.extractOfficeEntities(officeEntityPage, parentIdentifier));

    return officePage;
  }

  public List<Office> extractOfficeEntities(final Page<OfficeEntity> officeEntityPage, final String parentIdentifier) {
    final List<Office> offices = new ArrayList<>(officeEntityPage.getSize());
    officeEntityPage.forEach(officeEntity -> {
      final Office office = OfficeMapper.map(officeEntity);
      if (parentIdentifier != null) {
        office.setParentIdentifier(parentIdentifier);
      }
      offices.add(office);

      final Optional<AddressEntity> addressEntityOptional = this.addressRepository.findByOffice(officeEntity);
      addressEntityOptional.ifPresent(addressEntity -> office.setAddress(AddressMapper.map(addressEntity)));

      office.setExternalReferences(
          this.branchExists(office.getIdentifier())
              || this.hasEmployees(office.getIdentifier())
              || this.hasExternalReferences(office.getIdentifier())
      );
    });
    return offices;
  }

  public boolean hasExternalReferences(final String officeIdentifier) {
    return this.externalReferenceRepository.findByOfficeIdentifier(officeIdentifier)
        .stream()
        .anyMatch(externalReferenceEntity ->
            externalReferenceEntity.getState().equals(ExternalReference.State.ACTIVE.name()));
  }


  @Transactional
  public String createOffice(final Office office) throws ServiceException {
    this.createOffice(office, null);
    return office.getIdentifier();
  }

  @Transactional
  public String updateOffice(final Office office) throws ServiceException {
    final Optional<OfficeEntity> optionalOfficeEntity = this.officeRepository.findByIdentifier(office.getIdentifier());

    if (optionalOfficeEntity.isPresent()) {
      final OfficeEntity officeEntity = optionalOfficeEntity.get();
      if (office.getName() != null) {
        officeEntity.setName(office.getName());
      }

      if (office.getDescription() != null) {
        officeEntity.setDescription(office.getDescription());
      }

      officeEntity.setCreatedBy(userService.getPreferredUsername());
      officeEntity.setCreatedOn(Utils.utcNow());

      this.officeRepository.save(officeEntity);

      if (office.getAddress() != null) {
        this.setAddress(office.getIdentifier(), office.getAddress());
      }
      return office.getIdentifier();
    } else {
      throw ServiceException.notFound("Office {0} not found.", office.getIdentifier());
    }
  }

  @Transactional
  public String addBranch(final String parentIdentifier, final Office branch) {
    final Office parentOffice = new Office();
    parentOffice.setIdentifier(parentIdentifier);
    this.createOffice(branch, parentOffice);
    return branch.getIdentifier();
  }

  @Transactional
  public String deleteOffice(final String  identifier) {
    final Optional<OfficeEntity> optionalOfficeEntity = this.officeRepository.findByIdentifier(identifier);

    if (optionalOfficeEntity.isPresent()) {
      final OfficeEntity officeEntityToDelete = optionalOfficeEntity.get();
      final Optional<AddressEntity> optionalAddressEntity = this.addressRepository.findByOffice(officeEntityToDelete);
      optionalAddressEntity.ifPresent(this.addressRepository::delete);

      this.officeRepository.delete(officeEntityToDelete);

      this.externalReferenceRepository.deleteByOfficeIdentifier(identifier);
    }

    return identifier;
  }

  @SuppressWarnings("WeakerAccess")
  @Transactional
  public String setAddress(final String identifier, final Address address) {
    final Optional<OfficeEntity> optionalOfficeEntity = this.officeRepository.findByIdentifier(identifier);

    if (optionalOfficeEntity.isPresent()) {
      final OfficeEntity officeEntity = optionalOfficeEntity.get();
      final Optional<AddressEntity> optionalAddressEntity = this.addressRepository.findByOffice(officeEntity);
      if (optionalAddressEntity.isPresent()) {
        this.addressRepository.delete(optionalAddressEntity.get());
      }

      final AddressEntity addressEntity = AddressMapper.map(address);
      addressEntity.setOffice(officeEntity);
      this.addressRepository.save(addressEntity);

      officeEntity.setLastModifiedBy(userService.getPreferredUsername());
      officeEntity.setLastModifiedOn(Utils.utcNow());
      this.officeRepository.save(officeEntity);

      return identifier;
    } else {
      throw ServiceException.notFound("Office {0} not found.", identifier);
    }
  }

  @Transactional
  public String deleteAddress(final String identifier) {
    final Optional<OfficeEntity> optionalOfficeEntity = this.officeRepository.findByIdentifier(identifier);
    if (optionalOfficeEntity.isPresent()) {
      final OfficeEntity officeEntity = optionalOfficeEntity.get();
      if (officeEntity != null) {
        final Optional<AddressEntity> optionalAddressEntity = this.addressRepository.findByOffice(officeEntity);
        if (optionalAddressEntity.isPresent()) {
          this.addressRepository.delete(optionalAddressEntity.get());

          officeEntity.setLastModifiedBy(userService.getPreferredUsername());
          officeEntity.setLastModifiedOn(Utils.utcNow());
          this.officeRepository.save(officeEntity);
          return identifier;
        }
      } else {
        this.logger.info("Office {} not found.", identifier);
      }
    }
    return null;
  }

  @Transactional
  public String addExternalReference(final String officeIdentifier, ExternalReference externalReference) {


    final Optional<ExternalReferenceEntity> optionalExternalReference =
            this.externalReferenceRepository.findByOfficeIdentifierAndType(officeIdentifier, externalReference.getType());

    final ExternalReferenceEntity externalReferenceEntity;
    if (optionalExternalReference.isPresent()) {
      externalReferenceEntity = optionalExternalReference.get();
    } else {
      externalReferenceEntity = new ExternalReferenceEntity();
      externalReferenceEntity.setOfficeIdentifier(officeIdentifier);
      externalReferenceEntity.setType(externalReference.getType());
    }
    externalReferenceEntity.setState(externalReference.getState());

    this.externalReferenceRepository.save(externalReferenceEntity);

    return officeIdentifier;
  }

  private void createOffice(final Office office, final Office parentOffice) {
    if (this.officeRepository.existsByIdentifier(office.getIdentifier())) {
      this.logger.info("Office {} already exists.", office.getIdentifier());
      throw ServiceException.conflict("Office {0} already exists.", office.getIdentifier());
    }

    final String modificationUser = userService.getPreferredUsername();
    final Date modificationDate = Utils.utcNow();

    final OfficeEntity officeEntity = OfficeMapper.map(office);
    if (parentOffice != null) {
      final Optional<OfficeEntity> optionalParentOfficeEntity = this.officeRepository.findByIdentifier(parentOffice.getIdentifier());
      if (optionalParentOfficeEntity.isPresent()) {
        final OfficeEntity parentOfficeEntity = optionalParentOfficeEntity.get();
        officeEntity.setParentOfficeId(parentOfficeEntity.getId());
        parentOfficeEntity.setLastModifiedBy(modificationUser);
        parentOfficeEntity.setLastModifiedOn(modificationDate);
        this.officeRepository.save(parentOfficeEntity);
      }
    }

    officeEntity.setCreatedBy(modificationUser);
    officeEntity.setCreatedOn(modificationDate);

    final OfficeEntity savedOfficeEntity = this.officeRepository.save(officeEntity);

    if (office.getAddress() != null) {
      final AddressEntity addressEntity = AddressMapper.map(office.getAddress());
      addressEntity.setOffice(savedOfficeEntity);
      this.addressRepository.save(addressEntity);
    }
  }
}
