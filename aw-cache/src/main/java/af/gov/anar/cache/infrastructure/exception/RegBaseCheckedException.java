package af.gov.anar.cache.infrastructure.exception;

import af.gov.anar.cache.infrastructure.constant.ApplicationGenericConstants;
import af.gov.anar.lang.infrastructure.exception.common.BaseCheckedException;
import af.gov.anar.lang.infrastructure.exception.common.ExceptionUtils;
import af.gov.anar.lib.logger.Logger;
import af.gov.anar.cache.infrastructure.util.LoggerFactory;

/**
 * The class to handle all the checked exception in REG
 *
 */
public class RegBaseCheckedException extends BaseCheckedException {

    /**
     * Serializable Version Id
     */
    private static final long serialVersionUID = 7381314129809012005L;
    /**
     * Instance of {@link Logger}
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RegBaseCheckedException.class);

    /**
     * Constructs a new checked exception
     */
    public RegBaseCheckedException() {
        super();
    }

    /**
     * Constructs a new checked exception with the specified detail message and
     * error code.
     *
     * @param errorCode
     *            the error code
     * @param errorMessage
     *            the detail message.
     */
    public RegBaseCheckedException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
        LOGGER.error("TEMPLATE - CHECKED_EXCEPTION", ApplicationGenericConstants.APPLICATION_NAME,
                ApplicationGenericConstants.APPLICATION_ID, errorCode + "-->" + errorMessage);
    }

    /**
     * Constructs a new checked exception with the specified detail message and
     * error code.
     *
     * @param errorCode
     *            the error code
     * @param errorMessage
     *            the detail message
     * @param throwable
     *            the specified cause
     */
    public RegBaseCheckedException(String errorCode, String errorMessage, Throwable throwable) {
        super(errorCode, errorMessage, throwable);
        LOGGER.error("TEMPLATE - CHECKED_EXCEPTION", ApplicationGenericConstants.APPLICATION_NAME, ApplicationGenericConstants.APPLICATION_ID,
                errorCode + "-->" + errorMessage + "-->" + ExceptionUtils.getStackTrace(throwable));
    }
}
