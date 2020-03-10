package af.asr.youtap.util;


/**
 * ID needed for amounts above the
 * threshold specified in CTALAmount
 * 100 – Finger print required
 * 101 – Registered ID document
 * 102 – Customer PIN
 */
public interface CTALId {

    public static int FINGERPRINT_REQUEST = 100;
    public static int REGISTERED_ID_DOCUMENT = 101;
    public static int CUSTOMER_PIN = 102;
}
