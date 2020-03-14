
package af.asr.csc.mapper;

import af.asr.csc.domain.ContactDetail;
import af.asr.csc.model.ContactDetailEntity;
import org.springframework.stereotype.Component;

@Component
public  class ContactDetailMapper {

  private ContactDetailMapper() {
    super();
  }

  public static ContactDetailEntity map(final ContactDetail contactDetail) {
    final ContactDetailEntity contactDetailEntity = new ContactDetailEntity();
    contactDetailEntity.setType(contactDetail.getType());
    contactDetailEntity.setGroup(contactDetail.getGroup());
    contactDetailEntity.setValue(contactDetail.getValue());
    contactDetailEntity.setPreferenceLevel(contactDetail.getPreferenceLevel());
    contactDetailEntity.setValid(contactDetail.getValidated());
    return contactDetailEntity;
  }

  public static ContactDetail map(final ContactDetailEntity contactDetailEntity) {
    final ContactDetail contactDetail = new ContactDetail();
    contactDetail.setType(contactDetailEntity.getType());
    contactDetail.setGroup(contactDetailEntity.getGroup());
    contactDetail.setValue(contactDetailEntity.getValue());
    contactDetail.setPreferenceLevel(contactDetailEntity.getPreferenceLevel());
    contactDetail.setValidated(contactDetailEntity.getValid());
    return contactDetail;
  }
}
