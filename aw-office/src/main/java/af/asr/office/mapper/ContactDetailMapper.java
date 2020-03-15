
package af.asr.office.mapper;


import af.asr.office.domain.ContactDetail;
import af.asr.office.model.ContactDetailEntity;

public class ContactDetailMapper {

  private ContactDetailMapper() {
    super();
  }

  public static ContactDetailEntity map(final ContactDetail contactDetail) {
    final ContactDetailEntity contactDetailEntity = new ContactDetailEntity();
    contactDetailEntity.setType(contactDetail.getType());
    contactDetailEntity.setGroup(contactDetail.getGroup());
    contactDetailEntity.setValue(contactDetail.getValue());
    contactDetailEntity.setPreferenceLevel(contactDetail.getPreferenceLevel());
    return contactDetailEntity;
  }

  public static ContactDetail map(final ContactDetailEntity contactDetailEntity) {
    final ContactDetail contactDetail = new ContactDetail();
    contactDetail.setType(contactDetailEntity.getType());
    contactDetail.setGroup(contactDetailEntity.getGroup());
    contactDetail.setValue(contactDetailEntity.getValue());
    contactDetail.setPreferenceLevel(contactDetailEntity.getPreferenceLevel());
    return contactDetail;
  }
}
