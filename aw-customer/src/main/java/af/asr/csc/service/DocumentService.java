
package af.asr.csc.service;

import af.asr.csc.domain.CustomerDocument;
import af.asr.csc.mapper.DocumentMapper;
import af.asr.csc.model.DocumentEntity;
import af.asr.csc.model.DocumentPageEntity;
import af.asr.csc.repository.DocumentPageRepository;
import af.asr.csc.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DocumentService {
  private final DocumentRepository documentRepository;
  private final DocumentPageRepository documentPageRepository;

  @Autowired
  public DocumentService(
      final DocumentRepository documentRepository,
      final DocumentPageRepository documentPageRepository) {
    this.documentRepository = documentRepository;
    this.documentPageRepository = documentPageRepository;
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
}