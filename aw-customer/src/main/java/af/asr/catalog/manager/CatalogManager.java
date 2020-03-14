package af.asr.catalog.manager;


import af.asr.catalog.domain.Catalog;
import af.asr.catalog.domain.Field;
import af.asr.catalog.exception.*;
import af.gov.anar.api.annotation.ThrowsException;
import af.gov.anar.api.annotation.ThrowsExceptions;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@SuppressWarnings("unused")
@FeignClient(name="customer-v1", path="/customer/v1", configuration= FeignAutoConfiguration.class)
public interface CatalogManager {

  @RequestMapping(
      path = "/catalogs",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE
  )
  @ThrowsExceptions({
      @ThrowsException(status = HttpStatus.CONFLICT, exception = CatalogAlreadyExistsException.class),
      @ThrowsException(status = HttpStatus.BAD_REQUEST, exception = CatalogValidationException.class)
  })
  void createCatalog(@RequestBody final Catalog catalog);

  @RequestMapping(
      path = "/catalogs",
      method = RequestMethod.GET,
      produces = MediaType.ALL_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE
  )
  List<Catalog> fetchCatalogs();

  @RequestMapping(
      path = "/catalogs/{identifier}",
      method = RequestMethod.GET,
      produces = MediaType.ALL_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE
  )
  @ThrowsExceptions({
      @ThrowsException(status = HttpStatus.NOT_FOUND, exception = CatalogNotFoundException.class)
  })
  Catalog findCatalog(@PathVariable("identifier") final String identifier);

  @RequestMapping(
      path = "/catalogs/{identifier}",
      method = RequestMethod.DELETE,
      produces = MediaType.ALL_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE
  )
  @ThrowsExceptions({
      @ThrowsException(status = HttpStatus.NOT_FOUND, exception = CatalogNotFoundException.class),
      @ThrowsException(status = HttpStatus.BAD_REQUEST, exception = CatalogValidationException.class),
      @ThrowsException(status = HttpStatus.CONFLICT, exception = CatalogAlreadyInUseException.class)
  })
  void deleteCatalog(@PathVariable("identifier") final String identifier);

  @RequestMapping(
      path = "/catalogs/{catalogIdentifier}/fields/{fieldIdentifier}",
      method = RequestMethod.PUT,
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE
  )
  @ThrowsExceptions({
      @ThrowsException(status = HttpStatus.NOT_FOUND, exception = CatalogNotFoundException.class),
      @ThrowsException(status = HttpStatus.BAD_REQUEST, exception = CatalogValidationException.class),
      @ThrowsException(status = HttpStatus.CONFLICT, exception = FieldAlreadyInUseException.class)
  })
  void updateField(@PathVariable("catalogIdentifier") final String catalogIdentifier,
                   @PathVariable("fieldIdentifier") final String fieldIdentifier,
                   @RequestBody Field field);

  @RequestMapping(
      path = "/catalogs/{catalogIdentifier}/fields/{fieldIdentifier}",
      method = RequestMethod.DELETE,
      produces = MediaType.ALL_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE
  )
  @ThrowsExceptions({
      @ThrowsException(status = HttpStatus.NOT_FOUND, exception = CatalogNotFoundException.class),
      @ThrowsException(status = HttpStatus.BAD_REQUEST, exception = CatalogValidationException.class),
      @ThrowsException(status = HttpStatus.CONFLICT, exception = FieldAlreadyInUseException.class)
  })
  void deleteField(@PathVariable("catalogIdentifier") final String catalogIdentifier,
                   @PathVariable("fieldIdentifier") final String fieldIdentifier);

}
