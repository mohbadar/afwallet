package af.gov.anar.dck.odkx.data;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;

@JacksonXmlRootElement(localName = "tableDefinitionResource")
public class TableDefinitionResource extends TableDefinition {

    /**
     * Get this same TableDefinitionResource.
     */
    private String selfUri;

    /**
     * Get the TableResource for this tableId.
     */
    private String tableUri;

    @SuppressWarnings("unused")
    private TableDefinitionResource() {
    }

    public TableDefinitionResource(TableDefinition definition) {
        this(definition.getTableId(), definition.getSchemaETag(), definition.getColumns());
    }

    public TableDefinitionResource(String tableId, String schemaETag, ArrayList<Column> columns) {
        super(tableId, schemaETag, columns);
    }

    public String getSelfUri() {
        return this.selfUri;
    }

    public String getTableUri() {
        return this.tableUri;
    }

    public void setSelfUri(final String selfUri) {
        this.selfUri = selfUri;
    }

    public void setTableUri(final String tableUri) {
        this.tableUri = tableUri;
    }

}
