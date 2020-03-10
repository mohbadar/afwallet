
package af.gov.anar.lib.reportutil.service;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

public interface ReportingProcessService {

    Response processRequest(String reportName, MultivaluedMap<String, String> queryParams);

}