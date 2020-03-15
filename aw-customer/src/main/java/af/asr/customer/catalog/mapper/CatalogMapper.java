
package af.asr.customer.catalog.mapper;



import af.asr.customer.catalog.domain.Catalog;
import af.asr.customer.catalog.model.CatalogEntity;
import af.gov.anar.lang.validation.date.DateConverter;

import java.time.Clock;
import java.time.LocalDateTime;

public class CatalogMapper {

  private CatalogMapper() {
    super();
  }

  public static CatalogEntity map(final Catalog catalog) {
    final CatalogEntity catalogEntity = new CatalogEntity();
    catalogEntity.setIdentifier(catalog.getIdentifier());
    catalogEntity.setName(catalog.getName());
    catalogEntity.setDescription(catalog.getDescription());
//    catalogEntity.setCreatedBy(UserContextHolder.checkedGetUser());
    catalogEntity.setCreatedOn(LocalDateTime.now(Clock.systemUTC()));
    return catalogEntity;
  }

  public static Catalog map(final CatalogEntity catalogEntity) {
    final Catalog catalog = new Catalog();
    catalog.setIdentifier(catalogEntity.getIdentifier());
    catalog.setName(catalogEntity.getName());
    catalog.setDescription(catalogEntity.getDescription());
    catalog.setCreatedBy(catalogEntity.getCreatedBy());
    catalog.setCreatedOn(DateConverter.toIsoString(catalogEntity.getCreatedOn()));
    if (catalogEntity.getLastModifiedBy() != null) {
      catalog.setLastModifiedBy(catalogEntity.getLastModifiedBy());
      catalog.setLastModifiedOn(DateConverter.toIsoString(catalogEntity.getLastModifiedOn()));
    }
    return catalog;
  }
}
