package af.gov.anar.dck.infrastructure.util;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import af.gov.anar.dck.instance.model.Instance;
import af.gov.anar.dck.useradministration.model.User;

import javax.xml.parsers.ParserConfigurationException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Component
public class ExcelUtil {

    @Autowired
    private XmlParserUtil xmlParserUtil;

    @Autowired
    private DateTimeUtil dateTimeUtil;
    
    public List<String> getExcelColumns(XSSFSheet sheet) {
        List<String> columns = new ArrayList<String>();
        Row columnRow = sheet.getRow(0);
        for (Cell cell : columnRow) {
            columns.add(cell.getStringCellValue());
        }
        return columns;
    }

    public List<String> getInstanceColumnsForExcel(Instance instance) {
        List<String> columns = new ArrayList<String>();

        Map<String, Object> properties = instance.getProperties();
        Set<String> instanceCols = properties.keySet();

        columns.add("ID");
        columns.add("Title");
        columns.add("Created Date");
        columns.add("Owner");
        for (String InstanceColumn : instanceCols) {
            columns.add(InstanceColumn);
        }

        return columns;
    }

    public static String getCellValueAsString(Cell cell) {
        String strCellValue = null;
        if (cell != null) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    strCellValue = cell.toString();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        strCellValue = dateFormat.format(cell.getDateCellValue());
                    } else {
                        Double value = cell.getNumericCellValue();
                        Long longValue = value.longValue();
                        strCellValue = new String(longValue.toString());
                    }
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    strCellValue = new String(new Boolean(
                    cell.getBooleanCellValue()).toString());
                    break;
                case Cell.CELL_TYPE_BLANK:
                    strCellValue = "";
                    break;
            }
        }

        return strCellValue;
    }

    public List<Instance> populateInstancesFromExcelSheet(XSSFSheet sheet, Instance blankInstance, User user) throws ParserConfigurationException {
        List<Instance> instances = new ArrayList<Instance>();
        List<String> instanceCols = getInstanceColumnsForExcel(blankInstance);

        // rowIndex = 0 is column row and the data is start form index 1
        int rowIndex = 1;
        do {
            Row row = sheet.getRow(rowIndex);

            if(row == null ) {
                rowIndex = -1;
            } else {
                // check the first cell of row if it has value or not. If it has value means we should import the row else we will stop import function
                Cell cell0 = row.getCell(0);
                if(cell0 == null || cell0.getCellType() == Cell.CELL_TYPE_BLANK) {
                    rowIndex = -1;
                }

                Document instanceDoc = xmlParserUtil.parse(blankInstance.getXmlContent());
                Instance newInstance = new Instance();
                newInstance.setForm(blankInstance.getForm());
                newInstance.setEnvSlug(blankInstance.getEnvSlug());
      
                Cell cell1 = row.getCell(1);
                if(cell1 != null && cell1.getCellType() != Cell.CELL_TYPE_BLANK) {
                    newInstance.setTitle(cell1.getStringCellValue());
                }
                newInstance.setCreatedAt(dateTimeUtil.getCurrentDate());
                newInstance.setOwner(user);
                
                for (int i = 4; i < instanceCols.size(); i++) {
                    Cell cell = row.getCell(i);
                    String cellValue = getCellValueAsString(cell);
                    // Assign value to instance xml nodes
                    // NodeList nodeList = instanceDoc.getElementsByTagName(instanceCols.get(i));
                    Node nodeEl =  (Node) instanceDoc.getElementsByTagName(instanceCols.get(i)).item(0);
                    nodeEl = xmlParserUtil.setNodeValue(nodeEl, cellValue);
                }

                newInstance.setXmlContent(xmlParserUtil.convertToString(instanceDoc));
                instances.add(newInstance);
                rowIndex++;
            }
        } while(rowIndex != -1);

        return instances;
    }
}
