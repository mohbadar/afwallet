
package af.asr.customer.manager;

import af.asr.customer.domain.CustomerDocument;
import af.asr.customer.exception.CompletedDocumentCannotBeChangedException;
import af.asr.customer.exception.DocumentValidationException;
import af.gov.anar.api.annotation.ThrowsException;
import af.gov.anar.api.annotation.ThrowsExceptions;
import org.hibernate.validator.constraints.Range;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@FeignClient(name="customer-v1", path="/customer/v1", configuration= FeignAutoConfiguration.class)
public interface CustomerDocumentsManager {

  @RequestMapping(
      value = "/customers/{customeridentifier}/documents",
      method = RequestMethod.GET,
      produces = MediaType.ALL_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE
  )
  List<CustomerDocument> getDocuments(
      @PathVariable("customeridentifier") final String customerIdentifier);



  @RequestMapping(
      value = "/customers/{customeridentifier}/documents/{documentidentifier}",
      method = RequestMethod.GET,
      produces = MediaType.ALL_VALUE
  )
  CustomerDocument getDocument(
      @PathVariable("customeridentifier") final String customerIdentifier,
      @PathVariable("documentidentifier") final String documentIdentifier);



  @RequestMapping(
      value = "/customers/{customeridentifier}/documents/{documentidentifier}",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE
  )
  @ThrowsExceptions({
      @ThrowsException(status = HttpStatus.BAD_REQUEST, exception = DocumentValidationException.class)
  })
  void createDocument(
      @PathVariable("customeridentifier") final String customerIdentifier,
      @PathVariable("documentidentifier") final String documentIdentifier,
      @RequestBody final CustomerDocument customerDocument);


  @RequestMapping(
      value = "/customers/{customeridentifier}/documents/{documentidentifier}",
      method = RequestMethod.PUT,
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE
  )
  @ThrowsExceptions({
      @ThrowsException(status = HttpStatus.CONFLICT, exception = CompletedDocumentCannotBeChangedException.class),
      @ThrowsException(status = HttpStatus.BAD_REQUEST, exception = DocumentValidationException.class)
  })
  void changeDocument(
      @PathVariable("customeridentifier") final String customerIdentifier,
      @PathVariable("documentidentifier") final String documentIdentifier,
      @RequestBody final CustomerDocument customerDocument);


  @RequestMapping(
      value = "/customers/{customeridentifier}/documents/{documentidentifier}",
      method = RequestMethod.DELETE,
      produces = MediaType.ALL_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE
  )
  @ThrowsExceptions({
      @ThrowsException(status = HttpStatus.CONFLICT, exception = CompletedDocumentCannotBeChangedException.class)
  })
  void deleteDocument(
      @PathVariable("customeridentifier") final String customerIdentifier,
      @PathVariable("documentidentifier") final String documentIdentifier);


  /**
   * Once a document is "completed" its name and images cannot be changed again.  Only completed
   * documents should be referenced by other services.
   *
   * @param completed once this is set to true it cannot be changed back again.
   */
  @RequestMapping(
      value = "/customers/{customeridentifier}/documents/{documentidentifier}/completed",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE
  )
  @ThrowsExceptions({
      @ThrowsException(status = HttpStatus.CONFLICT, exception = CompletedDocumentCannotBeChangedException.class),
      @ThrowsException(status = HttpStatus.BAD_REQUEST, exception = DocumentValidationException.class),
  })
  void completeDocument(
      @PathVariable("customeridentifier") final String customerIdentifier,
      @PathVariable("documentidentifier") final String documentIdentifier,
      @RequestBody final Boolean completed);



  @RequestMapping(
      value = "/customers/{customeridentifier}/documents/{documentidentifier}/pages",
      method = RequestMethod.GET,
      produces = MediaType.ALL_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE
  )
  List<Integer> getDocumentPageNumbers(
      @PathVariable("customeridentifier") final String customerIdentifier,
      @PathVariable("documentidentifier") final String documentIdentifier);



  @RequestMapping(
      value = "/customers/{customeridentifier}/documents/{documentidentifier}/pages/{pagenumber}",
      method = RequestMethod.GET,
      produces = MediaType.ALL_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE
  )
  byte[] getDocumentPage(
      @PathVariable("customeridentifier") final String customerIdentifier,
      @PathVariable("documentidentifier") final String documentIdentifier,
      @PathVariable("pagenumber") final Integer pageNumber);



  @RequestMapping(
      value = "/customers/{customeridentifier}/documents/{documentidentifier}/pages/{pagenumber}",
      method = RequestMethod.POST,
      produces = MediaType.ALL_VALUE,
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE
  )
  @ThrowsExceptions({
      @ThrowsException(status = HttpStatus.CONFLICT, exception = CompletedDocumentCannotBeChangedException.class),
      @ThrowsException(status = HttpStatus.BAD_REQUEST, exception = DocumentValidationException.class),
  })
  void createDocumentPage(
      @PathVariable("customeridentifier") final String customerIdentifier,
      @PathVariable("documentidentifier") final String documentIdentifier,
      @PathVariable("pagenumber") @Range(min=0) final Integer pageNumber,
      @RequestBody final MultipartFile page);


  @RequestMapping(
      value = "/customers/{customeridentifier}/documents/{documentidentifier}/pages/{pagenumber}",
      method = RequestMethod.DELETE,
      produces = MediaType.ALL_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE
  )
  @ThrowsExceptions({
      @ThrowsException(status = HttpStatus.CONFLICT, exception = CompletedDocumentCannotBeChangedException.class)
  })
  void deleteDocumentPage(
      @PathVariable("customeridentifier") final String customerIdentifier,
      @PathVariable("documentidentifier") final String documentIdentifier,
      @PathVariable("pagenumber") @Range(min=0) final Integer pageNumber);
}