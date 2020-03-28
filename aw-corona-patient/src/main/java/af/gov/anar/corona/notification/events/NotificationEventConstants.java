
package af.gov.anar.corona.notification.events;

@SuppressWarnings("unused")
public interface NotificationEventConstants {
	
	String DESTINATION = "notification-v1";
	String SELECTOR_NAME = "action";
	String INITIALIZE = "initialize";
	
	String POST_SMS_CONFIGURATION = "post-sms-configuration";
	String POST_EMAIL_CONFIGURATION = "post-email-configuration";
	String POST_TEMPLATE = "post-template";
	String POST_SOURCE_APPLICATION = "post-source-application";
	String UPDATE_SMS_CONFIGURATION = "update-sms-configuration";
	String UPDATE_EMAIL_CONFIGURATION = "update-email-configuration";
	String DELETE_SMS_CONFIGURATION = "delete-sms-configuration";
	String DELETE_EMAIL_CONFIGURATION = "delete-email-configuration";
	String DELETE_TEMPLATE = "delete-template";
	String DELETE_SOURCE_APPLICATION = "delete-source-application";
	
	String SEND_EMAIL_NOTIFICATION = "post-send-email-notification";
	String SEND_SMS_NOTIFICATION = "post-send-sms-notification";
	
	String POST_ENABLE_CUSTOMER_CREATED_EVENT = "post-enable-customer-created-event";
	String POST_ENABLE_CUSTOMER_UPDATED_EVENT = "post-enable-customer-updated-event";
	String POST_ENABLE_CUSTOMER_CLOSED_EVENT = "post-enable-customer-closed-event";
	String POST_ENABLE_CUSTOMER_LOCKED_EVENT = "post-enable-customer-locked-event";
	String POST_ENABLE_CUSTOMER_ACTIVATED_EVENT = "post-enable-customer-activated-event";
	String POST_ENABLE_CUSTOMER__EVENT = "post-enable-customer--event";
	
	String SELECTOR_INITIALIZE = SELECTOR_NAME + " = '" + INITIALIZE + "'";
	String SELECTOR_POST_SMS_CONFIGURATION = SELECTOR_NAME + " = '" + POST_SMS_CONFIGURATION + "'";
	String SELECTOR_POST_EMAIL_CONFIGURATION = SELECTOR_NAME + " = '" + POST_EMAIL_CONFIGURATION + "'";
	String SELECTOR_POST_TEMPLATE = SELECTOR_NAME + " = '" + POST_TEMPLATE + "'";
	String SELECTOR_UPDATE_SMS_CONFIGURATION = SELECTOR_NAME + " = '" + UPDATE_SMS_CONFIGURATION + "'";
	String SELECTOR_UPDATE_EMAIL_CONFIGURATION = SELECTOR_NAME + " = '" + UPDATE_EMAIL_CONFIGURATION + "'";
	String SELECTOR_DELETE_SMS_CONFIGURATION = SELECTOR_NAME + " = '" + DELETE_SMS_CONFIGURATION + "'";
	String SELECTOR_DELETE_EMAIL_CONFIGURATION = SELECTOR_NAME + " = '" + DELETE_EMAIL_CONFIGURATION + "'";
	String SELECTOR_POST_SOURCE_APPLICATION = SELECTOR_NAME + " = '" + POST_SOURCE_APPLICATION + "'";
	String SELECTOR_DELETE_SOURCE_APPLICATION = SELECTOR_NAME + " = '" + DELETE_SOURCE_APPLICATION + "'";
	
	String SELECTOR_SEND_EMAIL_NOTIFICATION = SELECTOR_NAME + " = '" + SEND_EMAIL_NOTIFICATION + "'";
	String SELECTOR_SEND_SMS_NOTIFICATION = SELECTOR_NAME + " = '" + SEND_SMS_NOTIFICATION + "'";
	
	String TEST = "jms-test";
	String SELECTOR_TEST = SELECTOR_NAME + " = '" + TEST + "'";
}
