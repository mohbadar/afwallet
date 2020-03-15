
package af.asr.office.service;

import af.asr.infrastructure.service.UserService;
import af.asr.office.ServiceConstants;
import af.asr.office.domain.ContactDetail;
import af.asr.office.domain.Employee;
import af.asr.office.domain.EmployeePage;
import af.asr.office.mapper.ContactDetailMapper;
import af.asr.office.mapper.EmployeeMapper;
import af.asr.office.model.ContactDetailEntity;
import af.asr.office.model.EmployeeEntity;
import af.asr.office.model.OfficeEntity;
import af.asr.office.repository.ContactDetailRepository;
import af.asr.office.repository.EmployeeRepository;
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

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

  private final Logger logger;
  private final EmployeeRepository employeeRepository;
  private final ContactDetailRepository contactDetailRepository;
  private final OfficeRepository officeRepository;
  private final UserService userService;

  @Autowired
  public EmployeeService(@Qualifier(ServiceConstants.REST_LOGGER_NAME) final Logger logger,
                         final EmployeeRepository employeeRepository,
                         final ContactDetailRepository contactDetailRepository,
                         final OfficeRepository officeRepository, UserService userService) {
    super();
    this.logger = logger;
    this.employeeRepository = employeeRepository;
    this.contactDetailRepository = contactDetailRepository;
    this.officeRepository = officeRepository;
    this.userService = userService;
  }

  public Boolean employeeExists(final String code) {
    return this.employeeRepository.existsByIdentifier(code);
  }

  public Optional<Employee> findByCode(final String code) {
    final EmployeeEntity employeeEntity = this.employeeRepository.findByIdentifier(code);
    if (employeeEntity != null) {
      final Employee employee = EmployeeMapper.map(employeeEntity);
      employee.setContactDetails(this.findContactDetailsByEmployee(employeeEntity.getIdentifier()));
      return Optional.of(employee);
    } else {
      return Optional.empty();
    }
  }

  public EmployeePage findEmployees(final String term, final String officeIdentifier, final Pageable pageRequest) {

    final Page<EmployeeEntity> employeeEntityPage;
    if (term != null) {
      employeeEntityPage = this.employeeRepository.findByIdentifierContaining(term, pageRequest);
    } else if (officeIdentifier != null) {
      final OfficeEntity officeEntity = this.officeRepository.findByIdentifier(officeIdentifier)
          .orElseThrow(() -> ServiceException.notFound("Office {0} not found.", officeIdentifier));
      employeeEntityPage = this.employeeRepository.findByAssignedOffice(officeEntity, pageRequest);
    } else {
      employeeEntityPage = this.employeeRepository.findAll(pageRequest);
    }

    final EmployeePage employeePage = new EmployeePage();
    employeePage.setTotalPages(employeeEntityPage.getTotalPages());
    employeePage.setTotalElements(employeeEntityPage.getTotalElements());

    final List<Employee> employees = new ArrayList<>();
    employeePage.setEmployees(employees);
    employeeEntityPage.forEach(employeeEntity -> {
      final Employee employee = EmployeeMapper.map(employeeEntity);
      employees.add(employee);

      employee.setContactDetails(this.findContactDetailsByEmployee(employeeEntity.getIdentifier()));

    });

    return employeePage;
  }


  public List<ContactDetail> findContactDetailsByEmployee(final String identifier) {
    final EmployeeEntity employeeEntity = this.employeeRepository.findByIdentifier(identifier);
    if (employeeEntity == null) {
      throw ServiceException.notFound("Employee {0} not found.", identifier);
    }

    final List<ContactDetailEntity> contactDetailEntities = this.contactDetailRepository.findByEmployeeOrderByPreferenceLevelAsc(employeeEntity);
    if (contactDetailEntities != null && !contactDetailEntities.isEmpty()) {
      return contactDetailEntities
          .stream()
          .map(ContactDetailMapper::map)
          .collect(Collectors.toList());
    }
    return Collections.emptyList();
  }

  @Transactional
  public String createEmployee(final Employee employee)
          throws ServiceException {
    if (this.employeeRepository.existsByIdentifier(employee.getIdentifier())) {
      throw ServiceException.conflict("Employee {0} already exists.", employee.getIdentifier());
    }

    final EmployeeEntity employeeEntity = EmployeeMapper.map(employee);

    if (employee.getAssignedOffice() != null) {
      final Optional<OfficeEntity> officeEntity = this.officeRepository.findByIdentifier(employee.getAssignedOffice());
      if (officeEntity.isPresent()) {
        employeeEntity.setAssignedOffice(officeEntity.get());
      } else {
        throw ServiceException.notFound("Assigned office {0} not found.", employee.getAssignedOffice());
      }
    }
    employeeEntity.setCreatedBy(userService.getPreferredUsername());
    employeeEntity.setCreatedOn(Utils.utcNow());
    final EmployeeEntity savedEmployeeEntity = this.employeeRepository.save(employeeEntity);

    if (employee.getContactDetails() != null) {
      this.saveContactDetail(savedEmployeeEntity, employee.getContactDetails());
    }

    return employee.getIdentifier();
  }

  @Transactional
  public String deleteEmployee(final String code) {

    final EmployeeEntity employeeEntityToDelete = this.employeeRepository.findByIdentifier(code);
    if (employeeEntityToDelete != null) {
      this.deleteContactDetails(employeeEntityToDelete);
      this.employeeRepository.delete(employeeEntityToDelete);
    }
    return code;
  }

  @Transactional
  public String updateEmployee(final Employee employee) {
    final EmployeeEntity employeeEntity = this.employeeRepository.findByIdentifier(employee.getIdentifier());

    if (employee.getGivenName() != null) {
      employeeEntity.setGivenName(employee.getGivenName());
    }

    if (employee.getMiddleName() != null) {
      employeeEntity.setMiddleName(employee.getMiddleName());
    }

    if (employee.getSurname() != null) {
      employeeEntity.setSurname(employee.getSurname());
    }

    final OfficeEntity assignedOffice = employeeEntity.getAssignedOffice();
    final String currentIdentifier = assignedOffice != null ? assignedOffice.getIdentifier() : null;

    if (!Objects.equals(employee.getAssignedOffice(), currentIdentifier)) {
      final Optional<OfficeEntity> officeEntity = this.officeRepository.findByIdentifier(employee.getAssignedOffice());
      if (officeEntity.isPresent()) {
        employeeEntity.setAssignedOffice(officeEntity.get());
      } else {
        throw ServiceException.notFound("Assigned office {0} not found.", employee.getAssignedOffice());
      }
    }

    employeeEntity.setLastModifiedBy(userService.getPreferredUsername());
    employeeEntity.setLastModifiedOn(Utils.utcNow());
    this.employeeRepository.save(employeeEntity);

    return employee.getIdentifier();
  }

  @Transactional
  public String setContactDetail(final String employeeIdentifier, final List<ContactDetail> contactDetails)
          throws ServiceException {

    final EmployeeEntity employeeEntity = this.employeeRepository.findByIdentifier(employeeIdentifier);
    if (employeeEntity == null) {
      throw ServiceException.notFound("Employee {0} not found.", employeeIdentifier);
    }

    this.deleteContactDetails(employeeEntity);

    if (contactDetails != null && !contactDetails.isEmpty()) {
      this.saveContactDetail(employeeEntity, contactDetails);
    }

    employeeEntity.setLastModifiedBy(userService.getPreferredUsername());
    employeeEntity.setLastModifiedOn(Utils.utcNow());
    this.employeeRepository.save(employeeEntity);

    return employeeIdentifier;
  }

  @Transactional
  public String deleteContactDetail(final String identifier)
          throws ServiceException {

    final EmployeeEntity employeeEntity = this.employeeRepository.findByIdentifier(identifier);
    if (employeeEntity == null) {
      throw ServiceException.notFound("Employee {0} not found.", identifier);
    }

    if (this.deleteContactDetails(employeeEntity)) {
      employeeEntity.setLastModifiedBy(userService.getPreferredUsername());
      employeeEntity.setLastModifiedOn(Utils.utcNow());
      this.employeeRepository.save(employeeEntity);
    }

    return identifier;
  }

  private void saveContactDetail(final EmployeeEntity employeeEntity, List<ContactDetail> contactDetails) {
    if (contactDetails != null && !contactDetails.isEmpty()) {
      contactDetails.forEach(contactDetail -> {
        final ContactDetailEntity contactDetailEntity = ContactDetailMapper.map(contactDetail);
        contactDetailEntity.setEmployee(employeeEntity);
        this.contactDetailRepository.save(contactDetailEntity);
      });
    }
  }

  private boolean deleteContactDetails(final EmployeeEntity employeeEntity) {
    final List<ContactDetailEntity> contactDetailEntities = this.contactDetailRepository.findByEmployeeOrderByPreferenceLevelAsc(employeeEntity);
    if (contactDetailEntities != null && !contactDetailEntities.isEmpty()) {
      contactDetailEntities.forEach(this.contactDetailRepository::delete);
      return true;
    }
    return false;
  }
}
