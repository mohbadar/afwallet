package af.gov.anar.ebreshna.notification.mail.service;

import af.gov.anar.ebreshna.notification.mail.model.Mail;
import af.gov.anar.lib.mail.service.MailBuilder;
import af.gov.anar.lib.mail.service.MailService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class EmailService {

    @Autowired
    private MailService mailService;

    public void send(Mail mail) {
        try {
            MailBuilder
                    mailBuilder = new MailBuilder();
            mailBuilder.setSubject(mail.getSubject());
            mailBuilder.appendToText(mail.getContent());
            mailBuilder.setFrom(mail.getFrom());
            mailBuilder.addTo(mail.getTo());
            mailBuilder.addBcc(mail.getBcc());
            mailBuilder.addCc(mail.getCc());


            if(mail.isHasAttachment())
            {
                boolean attachmentAdded = mailBuilder.addAttachment(mail.getAttachmentPath(), mail.getAttachmentName());
                if (attachmentAdded) {
                    mailService.send(mailBuilder);
                }
            }
            mailService.send(mailBuilder);

        } catch (Exception e) {
            log.error("Failed to send mail", e);
        }
    }

}
