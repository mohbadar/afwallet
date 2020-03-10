package af.gov.anar.spm.infrastructure.exception;


import af.gov.anar.lang.infrastructure.exception.common.BaseUncheckedException;
import af.gov.anar.lang.infrastructure.exception.common.ExceptionUtils;
import af.gov.anar.lib.logger.Logger;
import af.gov.anar.spm.infrastructure.util.LoggerFactory;
import af.gov.anar.spm.infrastructure.constant.ApplicationGenericConstants;

/**
 * Class for handling the REG unchecked exception
 *
 */
public class RegBaseUncheckedException extends BaseUncheckedException {

    /**
     * Serializable Version Id
     */
    private static final long serialVersionUID = 276197701640260133L;
    /**
     * Instance of {@link Logger}
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RegBaseUncheckedException.class);

    /**
     * Constructs a new unchecked exception
     */
    public RegBaseUncheckedException() {
        super();
    }

    /**
     * Constructor
     *
     * @param errorCode
     *            the Error Code Corresponds to Particular Exception
     * @param errorMessage
     *            the Message providing the specific context of the error
     */
    public RegBaseUncheckedException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
        LOGGER.error("TEMPLATe - UNCHECKED_EXCEPTION", ApplicationGenericConstants.APPLICATION_NAME,
                ApplicationGenericConstants.APPLICATION_ID, errorCode + "-->" + errorMessage);
    }

    /**
     * Constructor
     *
     * @param errorCode
     *            the Error Code Corresponds to Particular Exception
     * @param errorMessage
     *            the Message providing the specific context of the error
     * @param throwable
     *            the Cause of exception
     */
    public RegBaseUncheckedException(String errorCode, String errorMessage, Throwable throwable) {
        super(errorCode, errorMessage, throwable);
        LOGGER.error("TEMPLATe - UNCHECKED_EXCEPTION", ApplicationGenericConstants.APPLICATION_NAME, ApplicationGenericConstants.APPLICATION_ID,
                errorCode + "-->" + errorMessage + "-->" + ExceptionUtils.getStackTrace(throwable));
    }
}
