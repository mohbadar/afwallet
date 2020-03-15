
    package af.asr.notification.listener;


import af.asr.customer.CustomerEventConstants;
import af.asr.customer.domain.ContactDetail;
import af.asr.customer.domain.Customer;
import af.asr.notification.ServiceConstants;
import af.asr.notification.service.NotificationService;
import af.gov.anar.lib.kafka.consumer.AnarKafkaConsumer;
import af.gov.anar.lib.kafka.producer.AnarKafkaProducer;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@SuppressWarnings("unused")
@Component
public class CustomerEventListener {

    private final NotificationService notificationService;
    private final Logger logger;

    @Autowired
    public CustomerEventListener(final NotificationService notificationService,
                                 @Qualifier(ServiceConstants.LOGGER_NAME) final Logger logger) {
        this.logger = logger;
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = {CustomerEventConstants.DESTINATION}, groupId = "notification")
    public void customerCreatedEvent(final String payload) {
        Customer customer = this.notificationService.findCustomer(payload).get();
        this.logger.info("{} has been invoked", "customerCreatedEvent");

        customer.getContactDetails().forEach(contact -> {
            if (contact.getType().equals(ContactDetail.Type.PHONE.toString())) {
                String receiverNumber = contact.getValue();
                // TODO: Localize message
                // TODO: Pass message to template
                notificationService.sendSMS(receiverNumber, "customerCreatedEvent");
            } else if (contact.getType().equals(ContactDetail.Type.EMAIL.toString())) {
                String emailAddress = contact.getValue();
                // TODO: Localize message
                // TODO: Pass message to template
                notificationService.sendEmail(
                        emailAddress,
                        "customerCreatedEvent",
                        payload);
            }
        });
    }

    @KafkaListener(topics = {CustomerEventConstants.DESTINATION}, groupId = "notification")
    public void customerUpdatedEvents(final String payload) {
        this.logger.info("{} has been invoked", "customerUpdatedEvent");
        Customer customer = this.notificationService.findCustomer(payload).get();

        customer.getContactDetails().forEach(contact -> {
            if (contact.getType().equals(ContactDetail.Type.PHONE.toString())) {
                String receiverNumber = contact.getValue();
                // TODO: Localize message
                // TODO: Pass message to template
                notificationService.sendSMS(receiverNumber,
                            "customerUpdatedEvent"
                            );
            } else if (contact.getType().equals(ContactDetail.Type.EMAIL.toString())) {
                String emailAddress = contact.getValue();
                // TODO: Localize message
                // TODO: Pass message to template
                notificationService.sendEmail(
                        emailAddress,
                        "customerUpdatedEvent",
                        payload);
            }
        });
    }


    @KafkaListener(topics = {CustomerEventConstants.DESTINATION}, groupId = "notification")
    public void customerActivatedEvent(final String payload) {
        this.logger.info("{} has been invoked", "customerActivatedEvent");
        Customer customer = this.notificationService.findCustomer(payload).get();

        customer.getContactDetails().forEach(contact -> {
            if (contact.getType().equalsIgnoreCase(ContactDetail.Type.PHONE.toString())) {
                String receiverNumber = contact.getValue();
                notificationService.sendSMS(receiverNumber,
                            "customerActivatedEvent");
            } else if (contact.getType().equals(ContactDetail.Type.EMAIL.toString())) {
                String emailAddress = contact.getValue();
                // TODO: Localize message
                // TODO: Pass message to template
                notificationService.sendEmail(
                        emailAddress,
                        "customerActivatedEvent",
                        payload);
            }
        });
    }
    @KafkaListener(topics = {CustomerEventConstants.DESTINATION}, groupId = "notification")
    public void customerLockedEvent(final String payload) {
        this.logger.info("{} has been invoked", "customerLockedEvent");
        Customer customer = this.notificationService.findCustomer(payload).get();

        customer.getContactDetails().forEach(contact -> {
            if (contact.getType().equals(ContactDetail.Type.PHONE.toString())) {
                String receiverNumber = contact.getValue();
                notificationService.sendSMS(receiverNumber,
                            "customerLockedEvent");
            } else if (contact.getType().equals(ContactDetail.Type.EMAIL.toString())) {
                String emailAddress = contact.getValue();
                // TODO: Localize message
                // TODO: Pass message to template
                notificationService.sendEmail(
                        emailAddress, "customerLockedEvent",
                        payload);
            }
        });
    }

    @KafkaListener(topics = {CustomerEventConstants.DESTINATION}, groupId = "notification")
    public void customerUnlockedEvent(final String payload) {
        this.logger.info("{} has been invoked", "customerUnlockedEvent");
        Customer customer = this.notificationService.findCustomer(payload).get();

        customer.getContactDetails().forEach(contact -> {
            if (contact.getType().equals(ContactDetail.Type.PHONE.toString())) {
                String receiverNumber = contact.getValue();
                notificationService.sendSMS(receiverNumber,
                            "customerUnlockedEvent");
            } else if (contact.getType().equals(ContactDetail.Type.EMAIL.toString())) {
                String emailAddress = contact.getValue();
                // TODO: Localize message
                // TODO: Pass message to template
                notificationService.sendEmail(
                        emailAddress,
                        "customerUnlockedEvent",
                        payload);
            }
        });
    }

    @KafkaListener(topics = {CustomerEventConstants.DESTINATION}, groupId = "notification")
    public void customerClosedEvent(final String payload) {
        this.logger.info("{} has been invoked", "customerClosedEvent");
        Customer customer = this.notificationService.findCustomer(payload).get();

        customer.getContactDetails().forEach(contact -> {
            if (contact.getType().equals(ContactDetail.Type.PHONE.toString())) {
                String receiverNumber = contact.getValue();
                notificationService.sendSMS(receiverNumber,
                            "customerClosedEvent");
            } else if (contact.getType().equals(ContactDetail.Type.EMAIL.toString())) {
                String emailAddress = contact.getValue();
                // TODO: Localize message
                // TODO: Pass message to template
                notificationService.sendEmail(
                        emailAddress, "customerClosedEvent",
                        payload);
            }
        });
    }

    @KafkaListener(topics = {CustomerEventConstants.DESTINATION}, groupId = "notification")
    public void customerReopenedEvent(                                     final String payload) {
        this.logger.info("{} has been invoked", "customerReopenedEvent");
        Customer customer = this.notificationService.findCustomer(payload).get();

        customer.getContactDetails().forEach(contact -> {
            if (contact.getType().equals(ContactDetail.Type.PHONE.toString())) {
                String receiverNumber = contact.getValue();
                notificationService.sendSMS(receiverNumber,
                            "customerReopenedEvent");
            } else if (contact.getType().equals(ContactDetail.Type.EMAIL.toString())) {
                String emailAddress = contact.getValue();
                // TODO: Localize message
                // TODO: Pass message to template
                notificationService.sendEmail(
                        emailAddress,
                        "customerReopenedEvent",
                        payload);
            }
        });
    }


    @KafkaListener(topics = {CustomerEventConstants.DESTINATION}, groupId = "notification")
    public void contactDetailsChangedEvent(final String payload) {
        this.logger.info("{} has been invoked", "contactDetailsChangedEvent");
        Customer customer = this.notificationService.findCustomer(payload).get();

        customer.getContactDetails().forEach(contact -> {
            if (contact.getType().equals(ContactDetail.Type.PHONE.toString())) {
                String receiverNumber = contact.getValue();
                notificationService.sendSMS(receiverNumber,
                            "contactDetailsChangedEvent");
            } else if (contact.getType().equals(ContactDetail.Type.EMAIL.toString())) {
                String emailAddress = contact.getValue();
                // TODO: Localize message
                // TODO: Pass message to template
                notificationService.sendEmail(
                        emailAddress,
                        "contactDetailsChangedEvent",
                        payload);
            }
        });
    }

    @KafkaListener(topics = {CustomerEventConstants.DESTINATION}, groupId = "notification")
    public void addressChangedEvent( final String payload) {
        this.logger.info("{} has been invoked", "addressChangedEvent");

        Customer customer = this.notificationService.findCustomer(payload).get();

        customer.getContactDetails().forEach(contact -> {
            if (contact.getType().equals(ContactDetail.Type.PHONE.toString())) {
                String receiverNumber = contact.getValue();
                notificationService.sendSMS(receiverNumber,
                            "addressChangedEvent");
            } else if (contact.getType().equals(ContactDetail.Type.EMAIL.toString())) {
                String emailAddress = contact.getValue();
                // TODO: Localize message
                // TODO: Pass message to template
                notificationService.sendEmail(
                        emailAddress,
                        "addressChangedEvent",
                        payload);
            }
        });
    }

}
