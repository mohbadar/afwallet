package af.gov.anar.dck.infrastructure.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.Column;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.stereotype.Component;

import af.gov.anar.dck.useradministration.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class DataTablesUtil {
    @Autowired
    UserService userService;

    @PersistenceContext
	private EntityManager entityManager;

    public Object get_data_list(String tableName, String sIndexColumn, DataTablesInput input) {
        input.getColumns();
        
        if(tableName == null)
            tableName = "";
        if(sIndexColumn == null)
            sIndexColumn = "id";
        // if(aColumns == null) {
        //     aColumns = new ArrayList<>();
        //     aColumns.add("id");
        // }

        List<Column> aColumnsList = input.getColumns();
        List<String> aColumns = new ArrayList<>();
        for(Column aCol : aColumnsList) {
            aColumns.add(aCol.getName());
        }

        if(aColumns.size() <= 0) {
            aColumns = new ArrayList<>();
            aColumns.add("id");
        }

        /* Total data set length */
        String sQuery = "SELECT COUNT('" + sIndexColumn + "') AS row_count FROM " + tableName;
        // log_message('debug', 'datatables query: ' . sQuery);

        System.out.println("sQuery: " + sQuery);

        Query q = entityManager.createNativeQuery(sQuery);
        BigInteger iTotal = (BigInteger) q.getSingleResult();
        // rResultTotal = $this->db->query($sQuery);
        // aResultTotal = $rResultTotal->row();
        // iTotal = $aResultTotal->row_count;
 
        /*
         * Paging
         */
        String sLimit = "";
        Integer iDisplayStart = input.getStart();
        Integer iDisplayLength = input.getLength();
        if (iDisplayStart != null && iDisplayLength != -1) {
            sLimit = "LIMIT " + iDisplayLength + " offset " + iDisplayStart;
        }

        System.out.println("sLimit: " + sLimit);
 
        // $uri_string = $_SERVER['QUERY_STRING'];
        // $uri_string = preg_replace("/\%5B/", '[', $uri_string);
        // $uri_string = preg_replace("/\%5D/", ']', $uri_string);
 
        // $get_param_array = explode("&", $uri_string);
        // $arr = array();
        // foreach ($get_param_array as $value) {
        //     $v = $value;
        //     $explode = explode("=", $v);
        //     $arr[$explode[0]] = $explode[1];
        // }
 
        // $index_of_columns = strpos($uri_string, "columns", 1);
        // $index_of_start = strpos($uri_string, "start");
        // $uri_columns = substr($uri_string, 7, ($index_of_start - $index_of_columns - 1));
        // $columns_array = explode("&", $uri_columns);
        // $arr_columns = array();
        // foreach ($columns_array as $value) {
        //     $v = $value;
        //     $explode = explode("=", $v);
        //     if (count($explode) == 2) {
        //         $arr_columns[$explode[0]] = $explode[1];
        //     } else {
        //         $arr_columns[$explode[0]] = '';
        //     }
        // }
 
        /*
         * Ordering
         */
        String sOrder = " ORDER BY ";
        // $sOrderIndex = $arr['order[0][column]'];
        Integer sOrderIndex = input.getOrder().get(0).getColumn();
        // $sOrderDir = $arr['order[0][dir]'];
        String sOrderDir = input.getOrder().get(0).getDir();
        // $bSortable_ = $arr_columns['columns[' . $sOrderIndex . '][orderable]'];
        Boolean bSortable_ = input.getColumns().get(sOrderIndex).getOrderable();
        if (bSortable_ == true) {
            sOrder += aColumns.get(sOrderIndex) + (sOrderDir == "asc" ? " asc " : " desc ");
        }
 
        System.out.println("sOrder: " + sOrder);

        /*
         * Filtering
         */
        String sWhere = "";
        // $sSearchVal = $arr['search[value]'];
        String sSearchVal = input.getSearch().getValue();
        if (sSearchVal != null && sSearchVal != "") {
            sWhere = "WHERE (";
            for (int i = 0; i < aColumns.size(); i++) {
                Boolean bSearchable_ = input.getColumns().get(i).getSearchable();
                if (bSearchable_ != null && bSearchable_ == true) {
                    // TODO: we have to escape the sSearchVal to avoid SQL Injection
                    sWhere += input.getColumns().get(i).getName() + " LIKE '%" + sSearchVal + "%' OR ";
                }
            }
            sWhere = sWhere.substring(0, sWhere.length() - 3);
            sWhere += ')';
        }

        System.out.println("sWhere: " + sWhere);
 
        /* Individual column filtering */
        // $sSearchReg = $arr['search[regex]'];
        Boolean sSearchReg = input.getSearch().getRegex();
        for (int i = 0; i < aColumns.size(); i++) {
            // $bSearchable_ = $arr['columns[' . $i . '][searchable]'];
            Boolean bSearchable_ = input.getColumns().get(i).getSearchable();
            if (bSearchable_ != null && bSearchable_ == true && sSearchReg != false) {
                // $search_val = $arr['columns[' . $i . '][search][value]'];
                String search_val = input.getColumns().get(i).getSearch().getValue();
                if (sWhere == "") {
                    sWhere = "WHERE ";
                } else {
                    sWhere += " AND ";
                }
                // TODO: we have to escape the sSearchVal to avoid SQL Injection
                sWhere += aColumns.get(i) + " LIKE '%" + search_val + "%' ";
            }
        }
 
        System.out.println("sWhere: " + sWhere);
 
        /*
         * SQL queries
         * Get data to display
         */
        String comma_seperated_columns = String.join(",", aColumns);
        sQuery = "SELECT " + comma_seperated_columns.replaceAll(" , ", " ") + " FROM " + tableName + " ";
        sQuery += sWhere;
        sQuery += sOrder;
        sQuery += sLimit;

        System.out.println("sQuery: " + sQuery);

        // log_message('debug', 'datatables query: ' . $sQuery);
        Query rResult = entityManager.createNativeQuery(sQuery, Tuple.class);
        
        /* Data set length after filtering */
        // sQuery = "SELECT FOUND_ROWS() AS length_count";

        // System.out.println("sQuery: " + sQuery);

        // log_message('debug', 'here is the datatables query: ' . $sQuery);
        // Query rResultFilterTotal = entityManager.createNativeQuery(sQuery);
        // BigInteger iFilteredTotal = (BigInteger) rResultFilterTotal.getSingleResult();
        

        /*
         * Output
         */
        Integer sEcho = input.getDraw();
        Map<String, Object> output = new LinkedHashMap<String, Object>();
        output.put("draw", sEcho);
        output.put("recordsTotal", iTotal);
        
 
        List<Tuple> aRows = rResult.getResultList();

        int iFilteredTotal = aRows.size();
        output.put("recordsFiltered", iFilteredTotal);

        List<Map> result = new ArrayList<>(aRows.size());
        for (Tuple aRow : aRows) {
            // List<Map> row = new ArrayList(aColumns.size());
            Map<String, Object> row = new LinkedHashMap<String, Object>();
            for (String col : aColumns) {
                // row.add(aRow.get(col));
                row.put(col, aRow.get(col));
            }
            result.add(row);
        }

        if(result.size() > 0) {
            output.put("data", result);
        } else {
            output.put("data", new ArrayList<>());
        }
 
        return output;
    }
}