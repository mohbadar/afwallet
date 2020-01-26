
package af.gov.anar.template.infrastructure.core.service;


import af.gov.anar.template.infrastructure.core.domain.EmailDetail;

public interface PlatformEmailService {

    void sendToUserAccount(String organisationName, String contactName,
                           String address, String username, String unencodedPassword);

    void sendDefinedEmail(EmailDetail emailDetails);

}
