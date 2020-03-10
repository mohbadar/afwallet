package af.asr.youtap.data;


import lombok.Data;

@Data
/**
 * FileDescriptor=(Name=offline_20160503120133_94876567.txt,InfoType=PURSELOGS)
 */
public class FileDescritorData {

    private String name;
    private String infoType;

    /**
     * FileDescriptor=(Name=offline_20160503120133_94876567.txt,InfoType=PURSELOGS)
     * @return
     */
    public String getData()
    {
        return String.format("FileDescriptor=(Name=%s,InfoType=%s)", this.name, this.infoType);
    }
}
