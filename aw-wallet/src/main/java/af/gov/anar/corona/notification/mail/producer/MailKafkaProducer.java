package af.gov.anar.corona.notification.mail.producer;

import af.gov.anar.corona.notification.mail.model.Mail;
import af.gov.anar.corona.notification.mail.util.Constant;
import af.gov.anar.lib.kafka.producer.AnarKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
public class MailKafkaProducer {

    @Autowired
    private AnarKafkaProducer<Mail> anarKafkaProducer;

    public ListenableFuture<SendResult<String, Mail>> produce(Mail obj)
    {
        return anarKafkaProducer.produce(Constant.MAIL_KAFKA_TOPIC, obj);
    }
}
