
package af.asr.customer.mapper;

import af.asr.customer.domain.IdentificationCardScan;
import af.asr.customer.model.IdentificationCardScanEntity;
import org.springframework.stereotype.Component;

@Component
public class IdentificationCardScanMapper {

  private IdentificationCardScanMapper() {
    super();
  }

  public static IdentificationCardScanEntity map(final IdentificationCardScan scan) {
    final IdentificationCardScanEntity entity = new IdentificationCardScanEntity();

    entity.setIdentifier(scan.getIdentifier());
    entity.setDescription(scan.getDescription());

    return entity;
  }

  public static IdentificationCardScan map(final IdentificationCardScanEntity entity) {
    final IdentificationCardScan scan = new IdentificationCardScan();

    scan.setIdentifier(entity.getIdentifier());
    scan.setDescription(entity.getDescription());

    return scan;
  }
}
