
package af.asr.csc.service;

import af.asr.csc.domain.CustomerDocument;
import af.asr.csc.events.DocumentEvent;
import af.asr.csc.events.DocumentPageEvent;
import af.asr.csc.mapper.DocumentMapper;
import af.asr.csc.model.DocumentEntity;
import af.asr.csc.model.DocumentPageEntity;
import af.asr.csc.repository.CustomerRepository;
import af.asr.csc.repository.DocumentPageRepository;
import af.asr.csc.repository.DocumentRepository;
import af.asr.infrastructure.service.UserService;
import af.gov.anar.lang.infrastructure.exception.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DocumentService {
  private final DocumentRepository documentRepository;
  private final DocumentPageRepository documentPageRepository;
  private final CustomerRepository customerRepository;
  private final UserService userService;

  @Autowired
  public DocumentService(
          final DocumentRepository documentRepository,
          final DocumentPageRepository documentPageRepository, CustomerRepository customerRepository, UserService userService) {
    this.documentRepository = documentRepository;
    this.documentPageRepository = documentPageRepository;
    this.customerRepository = customerRepository;
    this.userService = userService;
  }

  public Optional<DocumentPageEntity> findPage(
      final String customerIdentifier,
      final String documentIdentifier,
      final Integer pageNumber) {
    return this.documentPageRepository.findByCustomerIdAndDocumentIdentifierAndPageNumber(
        customerIdentifier,
        documentIdentifier,
        pageNumber);
  }

  public Stream<CustomerDocument> find(final String customerIdentifier) {
    final Stream<DocumentEntity> preMappedRet = this.documentRepository.findByCustomerId(customerIdentifier);
    return preMappedRet.map(DocumentMapper::map);
  }

  public Optional<CustomerDocument> findDocument(
      final String customerIdentifier,
      final String documentIdentifier) {
    return this.documentRepository.findByCustomerIdAndDocumentIdentifier(customerIdentifier, documentIdentifier)
        .map(DocumentMapper::map);
  }

  public boolean documentExists(
      final String customerIdentifier,
      final String documentIdentifier) {
    return findDocument(customerIdentifier, documentIdentifier).isPresent();
  }

  public Stream<Integer> findPageNumbers(
      final String customerIdentifier,
      final String documentIdentifier) {
    return documentPageRepository.findByCustomerIdAndDocumentIdentifier(customerIdentifier, documentIdentifier)
        .map(DocumentPageEntity::getPageNumber);
  }

  public boolean isDocumentCompleted(
      final String customerIdentifier,
      final String documentIdentifier) {
    return documentRepository.findByCustomerIdAndDocumentIdentifier(customerIdentifier, documentIdentifier)
        .map(DocumentEntity::getCompleted).orElse(true);
  }

  public boolean isDocumentMissingPages(
      final String customerIdentifier,
      final String documentIdentifier) {
    final List<Integer> pageNumbers = findPageNumbers(customerIdentifier, documentIdentifier)
        .sorted(Integer::compareTo)
        .collect(Collectors.toList());
    for (int i = 0; i < pageNumbers.size(); i++) {
      if (i != pageNumbers.get(i))
        return true;
    }

    return false;
  }


  @Transactional
  public DocumentPageEvent createDocumentPage(final String customerIdentifier, final String documentIdentifier, Integer pageNumber, MultipartFile multipartFile) throws IOException {
    final DocumentEntity documentEntity = documentRepository.findByCustomerIdAndDocumentIdentifier(
            customerIdentifier,
            documentIdentifier)
            .orElseThrow(() -> ServiceException.badRequest("Document not found"));

    final DocumentPageEntity documentPageEntity = DocumentMapper.map(multipartFile, pageNumber, documentEntity);
    documentPageRepository.save(documentPageEntity);

    return new DocumentPageEvent(customerIdentifier, documentIdentifier, pageNumber);
  }

  @Transactional
  public DocumentEvent createDocument(final String customerIdentifier, final CustomerDocument customerDocument) throws IOException {
    customerRepository.findByIdentifier(customerIdentifier)
            .map(customerEntity -> DocumentMapper.map(customerDocument, customerEntity))
            .ifPresent(documentRepository::save);

    return new DocumentEvent(customerIdentifier, customerDocument.getIdentifier());
  }

  @Transactional
  public DocumentEvent changeDocument(final String customerIdentifier, CustomerDocument customerDocument) throws IOException {
    final DocumentEntity existingDocument = documentRepository.findByCustomerIdAndDocumentIdentifier(
            customerIdentifier, customerDocument.getIdentifier())
            .orElseThrow(() ->
                    ServiceException.notFound("Document ''{0}'' for customer ''{1}'' not found",
                            customerDocument.getIdentifier(), customerIdentifier));

    customerRepository.findByIdentifier(customerIdentifier)
            .map(customerEntity -> DocumentMapper.map(customerDocument, customerEntity))
            .ifPresent(documentEntity -> {
              documentEntity.setId(existingDocument.getId());
              documentRepository.save(documentEntity);
            });

    return new DocumentEvent(customerIdentifier, customerDocument.getIdentifier());
  }

  @Transactional
  public DocumentEvent deleteDocument(final String customerIdentifier, final String documentIdentifier) throws IOException {
    final DocumentEntity existingDocument = documentRepository.findByCustomerIdAndDocumentIdentifier(
            customerIdentifier, documentIdentifier)
            .orElseThrow(() ->
                    ServiceException.notFound("Document ''{0}'' for customer ''{1}'' not found",
                            customerIdentifier, documentIdentifier));
    documentPageRepository.findByCustomerIdAndDocumentIdentifier(customerIdentifier, documentIdentifier)
            .forEach(documentPageRepository::delete);
    documentRepository.delete(existingDocument);

    return new DocumentEvent(customerIdentifier, documentIdentifier);
  }

  @Transactional
  public DocumentEvent completeDocument(final String customerIdentifier, final String documentIdentifier) throws IOException {
    final DocumentEntity documentEntity = documentRepository.findByCustomerIdAndDocumentIdentifier(
            customerIdentifier, documentIdentifier)
            .orElseThrow(() -> ServiceException.badRequest("Document not found"));

    documentEntity.setCreatedOn(LocalDateTime.now(Clock.systemUTC()));
    documentEntity.setCreatedBy(userService.getPreferredUsername());
    documentEntity.setCompleted(true);
    documentRepository.save(documentEntity);


    return new DocumentEvent(customerIdentifier, documentIdentifier);
  }

  @Transactional
  public DocumentPageEvent deleteDocumentPage(final String customerIdentifier, final String documentIdentifier, Integer pageNumber) throws IOException {
    documentPageRepository.findByCustomerIdAndDocumentIdentifierAndPageNumber(
            customerIdentifier,
            documentIdentifier,
            pageNumber)
            .ifPresent(documentPageRepository::delete);

    //No exception if it's not present, because why bother.  It's not present.  That was the goal.

    return new DocumentPageEvent(customerIdentifier, documentIdentifier, pageNumber);
  }
}