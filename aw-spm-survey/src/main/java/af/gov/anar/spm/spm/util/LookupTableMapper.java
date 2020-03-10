
package af.gov.anar.spm.spm.util;

import af.gov.anar.spm.spm.data.LookupTableData;
import af.gov.anar.spm.spm.data.LookupTableEntry;
import af.gov.anar.spm.spm.domain.LookupTable;
import af.gov.anar.spm.spm.domain.Survey;

import java.util.*;

public class LookupTableMapper {

    private LookupTableMapper() {
        super();
    }

    public static List<LookupTableData> map(final List<LookupTable> lookupTables) {

        final Map<String, LookupTableData> lookupTableDataMap = new HashMap<>();
        LookupTableData lookupTableData = null;
        if (lookupTables != null && !lookupTables.isEmpty()) {
            for (LookupTable lookupTable : lookupTables) {
                if ((lookupTableData = lookupTableDataMap.get(lookupTable.getKey())) == null) {
                    lookupTableData = new LookupTableData();
                    lookupTableDataMap.put(lookupTable.getKey(), lookupTableData);
                    lookupTableData.setKey(lookupTable.getKey());
                    lookupTableData.setDescription(lookupTable.getDescription());
                    lookupTableData.setEntries(new ArrayList<LookupTableEntry>());
                }
                lookupTableData.getEntries().add(new LookupTableEntry(lookupTable.getValueFrom(),
                        lookupTable.getValueTo(), lookupTable.getScore()));
            }
            return new ArrayList<>(lookupTableDataMap.values());
        }

        return Collections.EMPTY_LIST;
    }

    public static List<LookupTable> map(final LookupTableData lookupTableData, final Survey survey) {
        final List<LookupTable> lookupTables = new ArrayList<>();

        final List<LookupTableEntry> entries = lookupTableData.getEntries();

        if (entries != null) {
            for (LookupTableEntry entry : entries) {
                final LookupTable lookupTable = new LookupTable();
                lookupTables.add(lookupTable);
                lookupTable.setSurvey(survey);
                lookupTable.setKey(lookupTableData.getKey());
                lookupTable.setDescription(lookupTableData.getDescription());
                lookupTable.setValueFrom(entry.getValueFrom());
                lookupTable.setValueTo(entry.getValueTo());
                lookupTable.setScore(entry.getScore());
            }
        }

        return lookupTables;
    }
}
