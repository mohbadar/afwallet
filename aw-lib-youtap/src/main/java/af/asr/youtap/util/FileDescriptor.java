package af.asr.youtap.util;


/**
 * Contains information about a file that has been uploaded/downloaded.
 *
 * Example:
 * FileDescriptor=(Name=offline_20160503120133_94876567.txt,InfoType=PURSELOGS)
 */
public class FileDescriptor {

    private String name;
    private String infoType;

    public static class Builder{

        private String name;
        private String infoType;

    }
}
