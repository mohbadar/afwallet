
package af.asr.catalog.api;

import af.asr.catalog.domain.Catalog;
import af.asr.catalog.domain.Field;
import af.asr.catalog.service.CatalogService;
import af.asr.customer.util.ServiceConstants;
import af.gov.anar.lang.infrastructure.exception.service.ServiceException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/catalogs")
public class CatalogRestController {

  private final Logger logger;
  private final CatalogService catalogService;

  @Autowired
  public CatalogRestController(@Qualifier(ServiceConstants.LOGGER_NAME) final Logger logger,
                               final CatalogService catalogService) {
    super();
    this.logger = logger;
    this.catalogService = catalogService;
  }

   // @Permittable(value = AcceptedTokenType.TENANT, groupId = PermittableGroupIds.CATALOG)
  @RequestMapping(
      path = "",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE
  )
  public
  @ResponseBody
  ResponseEntity<Void> createCatalog(@RequestBody final Catalog catalog) {
    if (this.catalogService.catalogExists(catalog.getIdentifier())) {
      throw ServiceException.conflict("Catalog {0} already exists.", catalog.getIdentifier());
    }
    this.catalogService.createCatalog(catalog);
    return ResponseEntity.accepted().build();
  }

   // @Permittable(value = AcceptedTokenType.TENANT, groupId = PermittableGroupIds.CATALOG)
  @RequestMapping(
      path = "",
      method = RequestMethod.GET,
      consumes = MediaType.ALL_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public
  @ResponseBody
  ResponseEntity<List<Catalog>> fetchCatalogs() {
    return ResponseEntity.ok(this.catalogService.fetchAllCatalogs());
  }

   // @Permittable(value = AcceptedTokenType.TENANT, groupId = PermittableGroupIds.CATALOG)
  @RequestMapping(
      path = "/{identifier}",
      method = RequestMethod.GET,
      consumes = MediaType.ALL_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public
  @ResponseBody
  ResponseEntity<Catalog> findCatalog(@PathVariable("identifier") final String identifier) {
    return ResponseEntity.ok(
        this.catalogService.findCatalog(identifier)
            .orElseThrow(() -> ServiceException.notFound("Catalog {0} not found.", identifier))
    );
  }

   // @Permittable(value = AcceptedTokenType.TENANT, groupId = PermittableGroupIds.CATALOG)
  @RequestMapping(
      path = "/{identifier}",
      method = RequestMethod.DELETE,
      consumes = MediaType.ALL_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public
  @ResponseBody
  ResponseEntity<Void> deleteCatalog(@PathVariable("identifier") final String identifier) {
    if (this.catalogService.catalogInUse(identifier)) {
      throw ServiceException.conflict("Catalog {0} in use.", identifier);
    }

    this.catalogService.deleteCatalog(identifier);

    return ResponseEntity.accepted().build();
  }

   // @Permittable(value = AcceptedTokenType.TENANT, groupId = PermittableGroupIds.CATALOG)
  @RequestMapping(
      path = "/{catalogIdentifier}/fields/{fieldIdentifier}",
      method = RequestMethod.PUT,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public
  @ResponseBody
  ResponseEntity<Void> updateField(@PathVariable("catalogIdentifier") final String catalogIdentifier,
                                   @PathVariable("fieldIdentifier") final String fieldIdentifier,
                                   @RequestBody @Valid Field field) {
    if (this.catalogService.fieldInUse(catalogIdentifier, fieldIdentifier)) {
      throw ServiceException.conflict("Field {0} in use.", fieldIdentifier);
    }

    this.catalogService.changeField(catalogIdentifier, field);

    return ResponseEntity.accepted().build();
  }

   // @Permittable(value = AcceptedTokenType.TENANT, groupId = PermittableGroupIds.CATALOG)
  @RequestMapping(
      path = "/{catalogIdentifier}/fields/{fieldIdentifier}",
      method = RequestMethod.DELETE,
      consumes = MediaType.ALL_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public
  @ResponseBody
  ResponseEntity<Void> deleteField(@PathVariable("catalogIdentifier") final String catalogIdentifier,
                                   @PathVariable("fieldIdentifier") final String fieldIdentifier) {
    if (this.catalogService.fieldInUse(catalogIdentifier, fieldIdentifier)) {
      throw ServiceException.conflict("Field {0} in use.", fieldIdentifier);
    }

    this.catalogService.deleteField(catalogIdentifier, fieldIdentifier);

    return ResponseEntity.accepted().build();
  }
}
