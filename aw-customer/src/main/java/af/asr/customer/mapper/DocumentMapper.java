
package af.asr.customer.mapper;

import af.asr.customer.domain.CustomerDocument;
import af.asr.customer.model.CustomerEntity;
import af.asr.customer.model.DocumentEntity;
import af.asr.customer.model.DocumentPageEntity;
import af.gov.anar.lang.validation.date.DateConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Clock;
import java.time.LocalDateTime;

@Component
public class DocumentMapper {
  private DocumentMapper() {
    super();
  }


  public static DocumentPageEntity map(
      final MultipartFile multipartFile,
      final int pageNumber,
      final DocumentEntity documentEntity) throws IOException {
    final DocumentPageEntity ret = new DocumentPageEntity();
    ret.setDocument(documentEntity);
    ret.setPageNumber(pageNumber);
    ret.setImage(multipartFile.getBytes());
    ret.setSize(multipartFile.getSize());
    ret.setContentType(multipartFile.getContentType());
    return ret;
  }

  public static CustomerDocument map(final DocumentEntity documentEntity) {
    final CustomerDocument ret = new CustomerDocument();
    ret.setCompleted(documentEntity.getCompleted());
    ret.setCreatedBy(documentEntity.getCreatedBy());
    ret.setCreatedOn(DateConverter.toIsoString(documentEntity.getCreatedOn()));
    ret.setIdentifier(documentEntity.getIdentifier());
    ret.setDescription(documentEntity.getDescription());
    return ret;
  }

  public static DocumentEntity map(final CustomerDocument customerDocument, final CustomerEntity customerEntity) {
    final DocumentEntity ret = new DocumentEntity();
    ret.setCustomer(customerEntity);
    ret.setCompleted(false);
//    ret.setCreatedBy(UserContextHolder.checkedGetUser());
    ret.setCreatedOn(LocalDateTime.now(Clock.systemUTC()));
    ret.setIdentifier(customerDocument.getIdentifier());
    ret.setDescription(customerDocument.getDescription());
    return ret;
  }
}
