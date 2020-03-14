package af.gov.anar.dck.infrastructure.util;

import org.springframework.data.jpa.datatables.mapping.Column;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Component;

import java.util.List;

 @Component
public class QueryBuilderUtil {
    public String buildQuery(DataTablesInput input, String tableName) {
        String baseQuery = "SELECT ";
        List<Column> columns = input.getColumns();
        System.out.println(">>> base Query"+baseQuery);   
        System.out.println(">>> column"+columns.toString());

        for (int i = 0; i < columns.size() - 1; i++) {
            String name = columns.get(i).getName();
            if (!"null".equalsIgnoreCase(name) && name != null) {
                baseQuery += "f."+name + ", ";
            }
        }
        if (columns.get(columns.size() - 1).getName() != null
                && !"null".equalsIgnoreCase(columns.get(columns.size() - 1).getName())) {
            baseQuery += "f."+columns.get(columns.size() - 1).getName();
        }
        baseQuery += " FROM " + tableName;

        return baseQuery;
    }
}