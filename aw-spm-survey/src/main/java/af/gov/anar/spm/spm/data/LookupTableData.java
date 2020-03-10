
package af.gov.anar.spm.spm.data;

import java.util.List;

public class LookupTableData {

    private String key;
    private String description;
    private List<LookupTableEntry> entries;

    public LookupTableData() {
        super();
    }

    public LookupTableData(final String key, final String description,
                           final List<LookupTableEntry> entries) {
        super();
        this.key = key;
        this.description = description;
        this.entries = entries;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<LookupTableEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<LookupTableEntry> entries) {
        this.entries = entries;
    }
}
