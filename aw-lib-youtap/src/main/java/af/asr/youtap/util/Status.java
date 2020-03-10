package af.asr.youtap.util;

/**
 *Status code values 2 and 3 are displayed
 * and code values 1 and 4 are printed.
 * 0 – Successful
 * 1 – System error
 * 2 – Provisioning error
 * 3 – Input error
 * 4 – Declined
 * If the status is not successful the Message
 * field must be present in the response
 * message explaining the error.
 *
 * Example:
 *
 *
 * Status=2
 * Message=POSTe
 * rminal Not Found
 */
public interface Status {

    public static int SUCCESSFUL = 0;
    public static int SYSTEM_ERROR = 1;
    public static  int PROVISIONING_ERROR = 2;
    public static int INPUT_ERROR = 3;
    public static int DECLINED = 4;
}
