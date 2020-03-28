package af.gov.anar.corona.infrastructure.util;

import af.gov.anar.corona.infrastructure.constant.ApplicationGenericConstants;
import af.gov.anar.lib.logger.Logger;
import af.gov.anar.lib.logger.appender.RollingFileAppender;
import af.gov.anar.lib.logger.factory.Logfactory;

public class LoggerFactory {

    private static final RollingFileAppender ANAR_ROLLING_APPENDER = new RollingFileAppender();

    static {

        ANAR_ROLLING_APPENDER.setAppend(ApplicationGenericConstants.APPENDABLE);
        ANAR_ROLLING_APPENDER.setAppenderName(ApplicationGenericConstants.APPENDER_NAME);
        ANAR_ROLLING_APPENDER.setFileName(ApplicationGenericConstants.APPENDER_FILE_NAME);
        ANAR_ROLLING_APPENDER.setFileNamePattern(ApplicationGenericConstants.APPENDER_FILE_NAME_PATTERN);
        ANAR_ROLLING_APPENDER.setMaxFileSize(ApplicationGenericConstants.APPENDER_MAX_FILE_SIZE);
        ANAR_ROLLING_APPENDER.setTotalCap(ApplicationGenericConstants.APPENDER_TOTAL_CAPACITY);
        ANAR_ROLLING_APPENDER.setMaxHistory(ApplicationGenericConstants.APPENDER_MAX_HISTORY);
        ANAR_ROLLING_APPENDER.setImmediateFlush(ApplicationGenericConstants.APPENDER_IMMEDIATE_FLUSH);
        ANAR_ROLLING_APPENDER.setPrudent(ApplicationGenericConstants.APPENDER_PRUDENT);
    }

    /**
     * Get Logger for specific class
     *
     * @param className
     *            {@code Class} required classs where logger to be implemented
     * @return Logger {@code Logger}
     */
    public static Logger getLogger(Class<?> className) {
        return Logfactory.getDefaultRollingFileLogger(ANAR_ROLLING_APPENDER, className);
    }
}
