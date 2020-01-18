package af.gov.anar.template.infrastructure.util;

import af.gov.anar.lib.logger.Logger;
import af.gov.anar.lib.logger.appender.RollingFileAppender;
import af.gov.anar.lib.logger.factory.Logfactory;

public class LoggerFactory {

    private static final RollingFileAppender ANAR_ROLLING_APPENDER = new RollingFileAppender();

    static {

        ANAR_ROLLING_APPENDER.setAppend(true);
        ANAR_ROLLING_APPENDER.setAppenderName("org.apache.log4j.RollingFileAppender");
        ANAR_ROLLING_APPENDER.setFileName("logs/servicetemplate.log");
        ANAR_ROLLING_APPENDER.setFileNamePattern("logs/servicetemplate-%d{yyyy-MM-dd-HH}-%i.log");
        ANAR_ROLLING_APPENDER.setMaxFileSize("5MB");
        ANAR_ROLLING_APPENDER.setTotalCap("50MB");
        ANAR_ROLLING_APPENDER.setMaxHistory(10);
        ANAR_ROLLING_APPENDER.setImmediateFlush(true);
        ANAR_ROLLING_APPENDER.setPrudent(true);
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
