package af.gov.anar.template.infrastructure.constant;

public class ApplicationGenericConstants {


//    ::::::::::::::::::::::::: Serivice Constant ::::::::::::::::::::::::::::

    public static final String APPLICATION_ID = "SER_TEMP";
    public static final String APPLICATION_NAME = "SERVICE_TEMPLATE";
    // Application Name
    private static final String APP_NAME = "REGISTRATION - ";
    public static final String CLIENT_JAR_DECRYPTION = APP_NAME + "CLIENT_JAR_DECRYPTION - ";
    public static final String SOFTWARE_INSTALLATION_HANDLER = APP_NAME + "SOFTWARE_INSTALLATION_HANDLER";

//    :::::::::::::::::::: LOG FACTORY CONSTANTS ::::::::::::::::::::::::::::::::::
    public static  boolean  APPENDABLE =true;
    public static  String   APPENDER_NAME = "org.apache.log4j.RollingFileAppender";
    public static  String   APPENDER_FILE_NAME = "logs/servicetemplate.log";
    public static  String   APPENDER_FILE_NAME_PATTERN = "logs/servicetemplate-%d{yyyy-MM-dd-HH}-%i.log";
    public static  String   APPENDER_MAX_FILE_SIZE = "5MB";
    public static  String   APPENDER_TOTAL_CAPACITY = "50MB";
    public static  Integer  APPENDER_MAX_HISTORY  = 10;
    public static  boolean  APPENDER_IMMEDIATE_FLUSH = true;
    public static  boolean  APPENDER_PRUDENT = true;
}
