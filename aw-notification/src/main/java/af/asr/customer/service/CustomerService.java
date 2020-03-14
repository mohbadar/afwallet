
package af.asr.customer.service;

import af.asr.catalog.domain.Value;
import af.asr.catalog.model.CatalogEntity;
import af.asr.catalog.model.FieldEntity;
import af.asr.catalog.model.FieldValueEntity;
import af.asr.catalog.repository.CatalogRepository;
import af.asr.catalog.repository.FieldRepository;
import af.asr.catalog.repository.FieldValueRepository;
import af.asr.customer.domain.*;
import af.asr.customer.events.ScanEvent;
import af.asr.customer.mapper.CommandMapper;
import af.asr.customer.mapper.ContactDetailMapper;
import af.asr.customer.mapper.CustomerMapper;
import af.asr.customer.mapper.IdentificationCardMapper;
import af.asr.customer.model.*;
import af.asr.customer.repository.*;
import af.asr.customer.mapper.*;
import af.asr.infrastructure.service.UserService;
import af.gov.anar.lang.infrastructure.exception.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CustomerService {

  private final CustomerRepository customerRepository;
  private final IdentificationCardRepository identificationCardRepository;
  private final IdentificationCardScanRepository identificationCardScanRepository;
  private final PortraitRepository portraitRepository;
  private final ContactDetailRepository contactDetailRepository;
  private final FieldValueRepository fieldValueRepository;
  private final CommandRepository commandRepository;
  private final TaskDefinitionRepository taskDefinitionRepository;
  private final TaskInstanceRepository taskInstanceRepository;
  private final AddressRepository addressRepository;
  private final FieldRepository fieldRepository;
  private final CatalogRepository catalogRepository;
  private final UserService userService;
  private final TaskService taskService;

  @Autowired
  public CustomerService(final CustomerRepository customerRepository,
                         final IdentificationCardRepository identificationCardRepository,
                         final IdentificationCardScanRepository identificationCardScanRepository,
                         final PortraitRepository portraitRepository,
                         final ContactDetailRepository contactDetailRepository,
                         final FieldValueRepository fieldValueRepository,
                         final CommandRepository commandRepository,
                         final TaskDefinitionRepository taskDefinitionRepository,
                         final TaskInstanceRepository taskInstanceRepository,
                         final AddressRepository addressRepository,
                         final FieldRepository fieldRepository,
                         final CatalogRepository catalogRepository, UserService userService, TaskService taskService) {
    super();
    this.customerRepository = customerRepository;
    this.identificationCardRepository = identificationCardRepository;
    this.identificationCardScanRepository = identificationCardScanRepository;
    this.portraitRepository = portraitRepository;
    this.contactDetailRepository = contactDetailRepository;
    this.fieldValueRepository = fieldValueRepository;
    this.commandRepository = commandRepository;
    this.taskDefinitionRepository = taskDefinitionRepository;
    this.taskInstanceRepository = taskInstanceRepository;
    this.addressRepository = addressRepository;
    this.fieldRepository = fieldRepository;
    this.catalogRepository = catalogRepository;
    this.userService = userService;
    this.taskService = taskService;
  }

  public Boolean customerExists(final String identifier) {
    return this.customerRepository.existsByIdentifier(identifier);
  }

  public Boolean identificationCardExists(final String number) {
    return this.identificationCardRepository.existsByNumber(number);
  }

  public Boolean identificationCardScanExists(final String number, final String identifier) {
    return this.identificationCardRepository.findByNumber(number)
            .map(cardEntity -> this.identificationCardScanRepository.existsByIdentifierAndIdentificationCard(identifier, cardEntity))
            .orElse(false);
  }

  public Optional<Customer> findCustomer(final String identifier) {
    return customerRepository.findByIdentifier(identifier)
        .map(customerEntity -> {
          final Customer customer = CustomerMapper.map(customerEntity);
          customer.setAddress(AddressMapper.map(customerEntity.getAddress()));

          final List<ContactDetailEntity> contactDetailEntities = this.contactDetailRepository.findByCustomer(customerEntity);
          if (contactDetailEntities != null) {
            customer.setContactDetails(
                contactDetailEntities
                    .stream()
                    .map(ContactDetailMapper::map)
                    .collect(Collectors.toList())
            );
          }

          final List<FieldValueEntity> fieldValueEntities = this.fieldValueRepository.findByCustomer(customerEntity);
          if (fieldValueEntities != null) {
            customer.setCustomValues(
                fieldValueEntities
                    .stream()
                    .map(fieldValueEntity -> {
                      final Value value = new Value();
                      value.setValue(fieldValueEntity.getValue());
                      final FieldEntity fieldEntity = fieldValueEntity.getField();
                      value.setCatalogIdentifier(fieldEntity.getCatalog().getIdentifier());
                      value.setFieldIdentifier(fieldEntity.getIdentifier());
                      return value;
                    }).collect(Collectors.toList())
            );
          }

          return customer;
        });
  }

  public CustomerPage fetchCustomer(final String term, final Boolean includeClosed, final Pageable pageable) {
    final Page<CustomerEntity> customerEntities;
    if (includeClosed) {
      if (term != null) {
        customerEntities =
            this.customerRepository.findByIdentifierContainingOrGivenNameContainingOrSurnameContaining(term, term, term, pageable);
      } else {
        customerEntities = this.customerRepository.findAll(pageable);
      }
    } else {
      if (term != null) {
        customerEntities =
            this.customerRepository.findByCurrentStateNotAndIdentifierContainingOrGivenNameContainingOrSurnameContaining(
                Customer.State.CLOSED.name(), term, term, term, pageable);
      } else {
        customerEntities = this.customerRepository.findByCurrentStateNot(Customer.State.CLOSED.name(), pageable);
      }
    }

    final CustomerPage customerPage = new CustomerPage();
    customerPage.setTotalPages(customerEntities.getTotalPages());
    customerPage.setTotalElements(customerEntities.getTotalElements());
    if (customerEntities.getSize() > 0) {
      final ArrayList<Customer> customers = new ArrayList<>(customerEntities.getSize());
      customerPage.setCustomers(customers);
      customerEntities.forEach(customerEntity -> customers.add(CustomerMapper.map(customerEntity)));
    }

    return customerPage;
  }

  public final Stream<Command> fetchCommandsByCustomer(final String identifier) {
    return customerRepository.findByIdentifier(identifier)
        .map(commandRepository::findByCustomer)
        .orElse(Stream.empty())
        .map(CommandMapper::map);
  }

  public final Optional<PortraitEntity> findPortrait(final String identifier) {
    return customerRepository.findByIdentifier(identifier)
        .map(portraitRepository::findByCustomer);
  }

  public final Stream<IdentificationCard> fetchIdentificationCardsByCustomer(final String identifier) {
    return customerRepository.findByIdentifier(identifier)
        .map(identificationCardRepository::findByCustomer)
        .orElse(Stream.empty())
        .map(IdentificationCardMapper::map);
  }

  public Optional<IdentificationCard> findIdentificationCard(final String number) {
    final Optional<IdentificationCardEntity> identificationCardEntity = this.identificationCardRepository.findByNumber(number);

    return identificationCardEntity.map(IdentificationCardMapper::map);
  }

  public final List<IdentificationCardScan> fetchScansByIdentificationCard(final String number) {
    final Optional<IdentificationCardEntity> identificationCard = this.identificationCardRepository.findByNumber(number);

    return identificationCard.map(this.identificationCardScanRepository::findByIdentificationCard)
            .map(x -> x.stream().map(IdentificationCardScanMapper::map).collect(Collectors.toList()))
            .orElseGet(Collections::emptyList);
  }

  private Optional<IdentificationCardScanEntity> findIdentificationCardEntity(final String number, final String identifier) {
    final Optional<IdentificationCardEntity> cardEntity = this.identificationCardRepository.findByNumber(number);
    return cardEntity.flatMap(card -> this.identificationCardScanRepository.findByIdentifierAndIdentificationCard(identifier, card));
  }

  public Optional<IdentificationCardScan> findIdentificationCardScan(final String number, final String identifier) {
    return this.findIdentificationCardEntity(number, identifier).map(IdentificationCardScanMapper::map);
  }

  public Optional<byte[]> findIdentificationCardScanImage(final String number, final String identifier) {
    return this.findIdentificationCardEntity(number, identifier).map(IdentificationCardScanEntity::getImage);
  }

  public List<ProcessStep> getProcessSteps(final String customerIdentifier) {
    return customerRepository.findByIdentifier(customerIdentifier)
        .map(customerEntity -> {
          final List<ProcessStep> processSteps = new ArrayList<>();

          final Customer.State state = Customer.State.valueOf(customerEntity.getCurrentState());
          switch (state) {
            case PENDING:
              processSteps.add(this.buildProcessStep(customerEntity, Command.Action.ACTIVATE));
              processSteps.add(this.buildProcessStep(customerEntity, Command.Action.CLOSE));
              break;
            case ACTIVE:
              processSteps.add(this.buildProcessStep(customerEntity, Command.Action.LOCK));
              processSteps.add(this.buildProcessStep(customerEntity, Command.Action.CLOSE));
              break;
            case LOCKED:
              processSteps.add(this.buildProcessStep(customerEntity, Command.Action.UNLOCK));
              processSteps.add(this.buildProcessStep(customerEntity, Command.Action.CLOSE));
              break;
            case CLOSED:
              processSteps.add(this.buildProcessStep(customerEntity, Command.Action.REOPEN));
              break;
          }

          return processSteps;
        })
        .orElse(Collections.emptyList());
  }

  private ProcessStep buildProcessStep(final CustomerEntity customerEntity, final Command.Action action) {
    final ProcessStep processStep = new ProcessStep();

    final Command command = new Command();
    command.setAction(action.name());
    processStep.setCommand(command);

    final ArrayList<TaskDefinition> taskDefinitions = new ArrayList<>();
    this.taskDefinitionRepository.findByAssignedCommandsContaining(action.name())
        .forEach(taskDefinitionEntity ->
            this.taskInstanceRepository.findByCustomerAndTaskDefinition(customerEntity, taskDefinitionEntity)
            .forEach(taskInstanceEntity -> {
              if (taskInstanceEntity.getExecutedBy() == null) {
                taskDefinitions.add(TaskDefinitionMapper.map(taskDefinitionEntity));
              }
            }));
    processStep.setTaskDefinitions(taskDefinitions);

    return processStep;
  }


  @Transactional
  public String createCustomer(final Customer customer) {

    List<ContactDetailEntity> contactDetailEntities = new ArrayList<>();

    final AddressEntity savedAddress = this.addressRepository.save(AddressMapper.map(customer.getAddress()));

    final CustomerEntity customerEntity = CustomerMapper.map(customer);
    customerEntity.setCurrentState(Customer.State.PENDING.name());
    customerEntity.setAddress(savedAddress);
    final CustomerEntity savedCustomerEntity = this.customerRepository.save(customerEntity);

    if (customer.getContactDetails() != null) {

        for (ContactDetail contactDetail: customer.getContactDetails())
        {
            contactDetailEntities.add(ContactDetailMapper.map(contactDetail));
        }
        this.contactDetailRepository.saveAll(contactDetailEntities);
    }

    if (customer.getCustomValues() != null) {
      this.setCustomValues(customer, savedCustomerEntity);
    }

    return customer.getIdentifier();
  }


  @Transactional
   public String updateCustomer(final Customer customer) {

    final CustomerEntity customerEntity = findCustomerEntityOrThrow(customer.getIdentifier());

    customerEntity.setGivenName(customer.getGivenName());
    customerEntity.setMiddleName(customer.getMiddleName());
    customerEntity.setSurname(customer.getSurname());
    customerEntity.setAccountBeneficiary(customer.getAccountBeneficiary());
    customerEntity.setReferenceCustomer(customer.getReferenceCustomer());
    customerEntity.setAssignedOffice(customer.getAssignedOffice());
    customerEntity.setAssignedEmployee(customer.getAssignedEmployee());

    if (customer.getDateOfBirth() != null ) {
      final LocalDate newDateOfBirth = customer.getDateOfBirth().toLocalDate();
      if (customerEntity.getDateOfBirth() == null) {
        customerEntity.setDateOfBirth(Date.valueOf(newDateOfBirth));
      } else {
        final LocalDate dateOfBirth = customerEntity.getDateOfBirth().toLocalDate();
        if (!dateOfBirth.isEqual(newDateOfBirth)) {
          customerEntity.setDateOfBirth(Date.valueOf(newDateOfBirth));
        }
      }
    } else {
      customerEntity.setDateOfBirth(null);
    }

    if (customer.getCustomValues() != null) {
      this.fieldValueRepository.deleteByCustomer(customerEntity);
      this.fieldValueRepository.flush();
      this.setCustomValues(customer, customerEntity);
    }

    if (customer.getAddress() != null) {
      this.updateAddress(customer.getIdentifier(), customer.getAddress());
    }

    this.updateContactDetails(customer.getIdentifier(), customer.getContactDetails());

    customerEntity.setLastModifiedBy(userService.getPreferredUsername());
    customerEntity.setLastModifiedOn(LocalDateTime.now(Clock.systemUTC()));

    this.customerRepository.save(customerEntity);

    return customer.getIdentifier();
  }

  @Transactional
  public String updateContactDetails(final String identifier, List<ContactDetail> contactDetails) {
    final CustomerEntity customerEntity = findCustomerEntityOrThrow(identifier);
    customerEntity.setLastModifiedBy(userService.getPreferredUsername());
    customerEntity.setLastModifiedOn(LocalDateTime.now(Clock.systemUTC()));

    final List<ContactDetailEntity> oldContactDetails = this.contactDetailRepository.findByCustomer(customerEntity);
    this.contactDetailRepository.deleteAll(oldContactDetails);

    if (contactDetails != null) {
//      this.contactDetailRepository.save(
//              contactDetails
//                      .stream()
//                      .map(contact -> {
//                        final ContactDetailEntity newContactDetail = ContactDetailMapper.map(contact);
//                        newContactDetail.setCustomer(customerEntity);
//                        return newContactDetail;
//                      })
//                      .collect(Collectors.toList())
//      );
      List<ContactDetailEntity> contactDetailEntities = new ArrayList<>();
      for (ContactDetail contactDetail: contactDetails)
      {
        contactDetailEntities.add(ContactDetailMapper.map(contactDetail));
      }
      this.contactDetailRepository.saveAll(contactDetailEntities);
    }

    return identifier;
  }

  @Transactional

  public String activateCustomer(final String identifier, final String comment) {
    final CustomerEntity customerEntity = findCustomerEntityOrThrow(identifier);

    if (this.taskService.openTasksForCustomerExist(customerEntity, Command.Action.ACTIVATE.name())) {
      throw ServiceException.conflict("Open Tasks for customer {0} exists.", identifier);
    }

    customerEntity.setCurrentState(Customer.State.ACTIVE.name());
    if (customerEntity.getApplicationDate() == null) {
      customerEntity.setApplicationDate(LocalDate.now(Clock.systemUTC()));
    }
    customerEntity.setLastModifiedBy(userService.getPreferredUsername());
    customerEntity.setLastModifiedOn(LocalDateTime.now(Clock.systemUTC()));

    final CustomerEntity savedCustomerEntity = this.customerRepository.save(customerEntity);

    this.commandRepository.save(
            CommandMapper.create(savedCustomerEntity, Command.Action.ACTIVATE.name(), comment)    );

    return identifier;
  }

  @Transactional
  public String lockCustomer(final String identifier, final String comment) {
    final CustomerEntity customerEntity = findCustomerEntityOrThrow(identifier);

    customerEntity.setCurrentState(Customer.State.LOCKED.name());
    customerEntity.setLastModifiedBy(userService.getPreferredUsername());
    customerEntity.setLastModifiedOn(LocalDateTime.now(Clock.systemUTC()));

    final CustomerEntity savedCustomerEntity = this.customerRepository.save(customerEntity);

    this.commandRepository.save(
            CommandMapper.create(savedCustomerEntity, Command.Action.LOCK.name(), comment)
    );

    this.taskService.onCustomerCommand(savedCustomerEntity, Command.Action.UNLOCK);

    return identifier;
  }

  @Transactional
  public String unlockCustomer(final String identifier, final String comment) {
    final CustomerEntity customerEntity = findCustomerEntityOrThrow(identifier);

    if (this.taskService.openTasksForCustomerExist(customerEntity, Command.Action.UNLOCK.name())) {
      throw ServiceException.conflict("Open Tasks for customer {0} exists.", identifier);
    }

    customerEntity.setCurrentState(Customer.State.ACTIVE.name());
    customerEntity.setLastModifiedBy(userService.getPreferredUsername());
    customerEntity.setLastModifiedOn(LocalDateTime.now(Clock.systemUTC()));

    final CustomerEntity savedCustomerEntity = this.customerRepository.save(customerEntity);

    this.commandRepository.save(
            CommandMapper.create(savedCustomerEntity, Command.Action.UNLOCK.name(), comment)
    );

    return identifier;
  }

  @Transactional
  public String closeCustomer(final String identifier, final String comment) {
    final CustomerEntity customerEntity = findCustomerEntityOrThrow(identifier);

    customerEntity.setCurrentState(Customer.State.CLOSED.name());
    customerEntity.setLastModifiedBy(userService.getPreferredUsername());
    customerEntity.setLastModifiedOn(LocalDateTime.now(Clock.systemUTC()));

    final CustomerEntity savedCustomerEntity = this.customerRepository.save(customerEntity);

    this.commandRepository.save(
            CommandMapper.create(savedCustomerEntity, Command.Action.CLOSE.name(), comment)
    );

    this.taskService.onCustomerCommand(savedCustomerEntity, Command.Action.REOPEN);

    return identifier;
  }

  @Transactional
  public String reopenCustomer(final String identifier, final String comment) {
    final CustomerEntity customerEntity = findCustomerEntityOrThrow(identifier);

    if (this.taskService.openTasksForCustomerExist(customerEntity, Command.Action.REOPEN.name())) {
      throw ServiceException.conflict("Open Tasks for customer {0} exists.", identifier);
    }

    customerEntity.setCurrentState(Customer.State.ACTIVE.name());
    customerEntity.setLastModifiedBy(userService.getPreferredUsername());
    customerEntity.setLastModifiedOn(LocalDateTime.now(Clock.systemUTC()));

    final CustomerEntity savedCustomerEntity = this.customerRepository.save(customerEntity);

    this.commandRepository.save(
            CommandMapper.create(savedCustomerEntity, Command.Action.REOPEN.name(), comment)
    );

    return identifier;
  }


  @Transactional
  public String createIdentificationCard(final String identifier, IdentificationCard identificationCard) {
    final CustomerEntity customerEntity = findCustomerEntityOrThrow(identifier);

    final IdentificationCardEntity identificationCardEntity = IdentificationCardMapper.map(identificationCard);

    identificationCardEntity.setCustomer(customerEntity);
    identificationCardEntity.setCreatedBy(userService.getPreferredUsername());
    identificationCardEntity.setCreatedOn(LocalDateTime.now(Clock.systemUTC()));

    this.identificationCardRepository.save(identificationCardEntity);

    customerEntity.setLastModifiedBy(userService.getPreferredUsername());
    customerEntity.setLastModifiedOn(LocalDateTime.now(Clock.systemUTC()));

    this.customerRepository.save(customerEntity);

    return identificationCardEntity.getNumber();
  }

  @Transactional
  public String updateIdentificationCard(final String identifier, String number, IdentificationCard identificationCard1) {
    final Optional<IdentificationCardEntity> optionalIdentificationCardEntity = this.identificationCardRepository.findByNumber(number);

    final IdentificationCardEntity identificationCard = IdentificationCardMapper.map(identificationCard1);

    optionalIdentificationCardEntity.ifPresent(identificationCardEntity -> {
      identificationCardEntity.setIssuer(identificationCard.getIssuer());
      identificationCardEntity.setType(identificationCard.getType());
      identificationCardEntity.setExpirationDate(identificationCard.getExpirationDate());
      identificationCardEntity.setLastModifiedBy(userService.getPreferredUsername());
      identificationCardEntity.setLastModifiedOn(LocalDateTime.now(Clock.systemUTC()));

      this.identificationCardRepository.save(identificationCardEntity);

      final CustomerEntity customerEntity = identificationCardEntity.getCustomer();

      customerEntity.setLastModifiedBy(userService.getPreferredUsername());
      customerEntity.setLastModifiedOn(LocalDateTime.now(Clock.systemUTC()));

      this.customerRepository.save(customerEntity);
    });

    return number;
  }

  @Transactional
   public String deleteIdentificationCard(final String  number) throws IOException {
    final Optional<IdentificationCardEntity> optionalIdentificationCardEntity = this.identificationCardRepository.findByNumber(number);

    optionalIdentificationCardEntity.ifPresent(identificationCardEntity -> {

      final List<IdentificationCardScanEntity> cardScanEntities = this.identificationCardScanRepository.findByIdentificationCard(identificationCardEntity);

      this.identificationCardScanRepository.deleteAll(cardScanEntities);

      this.identificationCardRepository.delete(identificationCardEntity);

      final CustomerEntity customerEntity = identificationCardEntity.getCustomer();

      customerEntity.setLastModifiedBy(userService.getPreferredUsername());
      customerEntity.setLastModifiedOn(LocalDateTime.now(Clock.systemUTC()));

      this.customerRepository.save(customerEntity);
    });
    return number;
  }
  @Transactional
  public String updateAddress(final String identifier, Address address) {
    final CustomerEntity customerEntity = findCustomerEntityOrThrow(identifier);
    customerEntity.setLastModifiedBy(userService.getPreferredUsername());
    customerEntity.setLastModifiedOn(LocalDateTime.now(Clock.systemUTC()));

    final AddressEntity oldAddressEntity = customerEntity.getAddress();

    final AddressEntity newAddressEntity = this.addressRepository.save(AddressMapper.map(address));

    customerEntity.setAddress(newAddressEntity);
    this.customerRepository.save(customerEntity);

    this.addressRepository.delete(oldAddressEntity);

    return identifier;
  }


  @Transactional
  public ScanEvent createIdentificationCardScan(final String number, IdentificationCardScan scan, MultipartFile imageMultipartFile) throws Exception {
    final Optional<IdentificationCardEntity> identificationCardEntity = this.identificationCardRepository.findByNumber(number);

    final IdentificationCardEntity cardEntity = identificationCardEntity.orElseThrow(() -> ServiceException.notFound("Identification card {0} not found.", number));

    final IdentificationCardScanEntity identificationCardScanEntity = IdentificationCardScanMapper.map(scan);

    final MultipartFile image = imageMultipartFile;

    final LocalDateTime now = LocalDateTime.now(Clock.systemUTC());

    identificationCardScanEntity.setImage(image.getBytes());
    identificationCardScanEntity.setContentType(image.getContentType());
    identificationCardScanEntity.setSize(image.getSize());
    identificationCardScanEntity.setIdentificationCard(cardEntity);
    identificationCardScanEntity.setCreatedBy(userService.getPreferredUsername());
    identificationCardScanEntity.setCreatedOn(now);

    identificationCardScanRepository.save(identificationCardScanEntity);

    cardEntity.setLastModifiedBy(userService.getPreferredUsername());
    cardEntity.setLastModifiedOn(now);

    identificationCardRepository.save(cardEntity);

    return new ScanEvent(number, scan.getIdentifier());
  }

  @Transactional
  public ScanEvent deleteIdentificationCardScan(final String identifier, final String number) {
    final Optional<IdentificationCardEntity> cardEntity = this.identificationCardRepository.findByNumber(number);
    final Optional<IdentificationCardScanEntity> scanEntity = cardEntity
            .flatMap(entity -> this.identificationCardScanRepository.findByIdentifierAndIdentificationCard(identifier, entity));

    scanEntity.ifPresent(identificationCardScanEntity -> {

      this.identificationCardScanRepository.delete(identificationCardScanEntity);

      final IdentificationCardEntity identificationCard = identificationCardScanEntity.getIdentificationCard();

      identificationCard.setLastModifiedBy(userService.getPreferredUsername());
      identificationCard.setLastModifiedOn(LocalDateTime.now(Clock.systemUTC()));

      this.identificationCardRepository.save(identificationCard);
    });

    return new ScanEvent(number, identifier);
  }

  @Transactional
  public String createPortrait(final String identifier, MultipartFile multipartFile) throws IOException {
    if(multipartFile == null) {
      return null;
    }

    final CustomerEntity customerEntity = findCustomerEntityOrThrow(identifier);

    final PortraitEntity portraitEntity = PortraitMapper.map(multipartFile);
    portraitEntity.setCustomer(customerEntity);
    this.portraitRepository.save(portraitEntity);

    customerEntity.setLastModifiedBy(userService.getPreferredUsername());
    customerEntity.setLastModifiedOn(LocalDateTime.now(Clock.systemUTC()));

    this.customerRepository.save(customerEntity);

    return identifier;
  }

  @Transactional
  public String deletePortrait(final String identifier) throws IOException {
    final CustomerEntity customerEntity = findCustomerEntityOrThrow(identifier);

    this.portraitRepository.deleteByCustomer(customerEntity);

    customerEntity.setLastModifiedBy(userService.getPreferredUsername());
    customerEntity.setLastModifiedOn(LocalDateTime.now(Clock.systemUTC()));

    this.customerRepository.save(customerEntity);

    return identifier;
  }

  private void setCustomValues(final Customer customer, final CustomerEntity savedCustomerEntity) {

    List<FieldValueEntity> fieldValueEntities = new ArrayList<>();
    for (Value value : customer.getCustomValues())
    {
      final Optional<CatalogEntity> catalogEntity = this.catalogRepository.findByIdentifier(value.getCatalogIdentifier());
      final Optional<FieldEntity> fieldEntity = this.fieldRepository.findByCatalogAndIdentifier(catalogEntity.get(),value.getFieldIdentifier());
      final FieldValueEntity fieldValueEntity = FieldValueMapper.map(value);
      fieldValueEntity.setCustomer(savedCustomerEntity);
      fieldValueEntity.setField(
              fieldEntity.orElseThrow(() -> ServiceException.notFound("Field {0} not found.", value.getFieldIdentifier())));
      fieldValueEntities.add(fieldValueEntity);

    }

    this.fieldValueRepository.saveAll(fieldValueEntities);
//    this.fieldValueRepository.save(
//            customer.getCustomValues()
//                    .stream()
//                    .map(value -> {
//                      final Optional<CatalogEntity> catalog =
//                              this.catalogRepository.findByIdentifier(value.getCatalogIdentifier());
//                      final Optional<FieldEntity> field =
//                              this.fieldRepository.findByCatalogAndIdentifier(
//                                      catalog.orElseThrow(() -> ServiceException.notFound("Catalog {0} not found.", value.getCatalogIdentifier())),
//                                      value.getFieldIdentifier());
//                      final FieldValueEntity fieldValueEntity = FieldValueMapper.map(value);
//                      fieldValueEntity.setCustomer(savedCustomerEntity);
//                      fieldValueEntity.setField(
//                              field.orElseThrow(() -> ServiceException.notFound("Field {0} not found.", value.getFieldIdentifier())));
//                      return fieldValueEntity;
//                    })
//                    .collect(Collectors.toList())
//    );
  }

  private CustomerEntity findCustomerEntityOrThrow(String identifier) {
    return this.customerRepository.findByIdentifier(identifier)
            .orElseThrow(() -> ServiceException.notFound("Customer ''{0}'' not found", identifier));
  }



}
