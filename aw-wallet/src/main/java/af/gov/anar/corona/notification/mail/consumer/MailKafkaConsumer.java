package af.gov.anar.corona.notification.mail.consumer;

import af.gov.anar.corona.notification.mail.model.Mail;
import af.gov.anar.corona.notification.mail.service.EmailService;
import af.gov.anar.corona.notification.mail.util.Constant;
import af.gov.anar.lib.kafka.consumer.AnarKafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MailKafkaConsumer implements AnarKafkaConsumer<Mail> {

    @Autowired
    private EmailService emailService;

    @Override
    @KafkaListener(topics = {Constant.MAIL_KAFKA_TOPIC}, groupId = Constant.MAIL_KAFKA_GROUP)
    public void consume(Mail obj) {
        emailService.send(obj);
    }
}
