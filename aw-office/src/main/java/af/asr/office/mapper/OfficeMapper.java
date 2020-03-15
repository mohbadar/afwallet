
package af.asr.office.mapper;


import af.asr.office.domain.Office;
import af.asr.office.model.OfficeEntity;

public final class OfficeMapper {

  private OfficeMapper() {
    super();
  }

  public static OfficeEntity map(final Office office) {
    final OfficeEntity officeEntity = new OfficeEntity();
    officeEntity.setIdentifier(office.getIdentifier());
    officeEntity.setName(office.getName());
    officeEntity.setDescription(office.getDescription());
    return officeEntity;
  }

  public static Office map(final OfficeEntity officeEntity) {
    final Office office = new Office();
    office.setIdentifier(officeEntity.getIdentifier());
    office.setName(officeEntity.getName());
    office.setDescription(officeEntity.getDescription());
    return office;
  }
}
