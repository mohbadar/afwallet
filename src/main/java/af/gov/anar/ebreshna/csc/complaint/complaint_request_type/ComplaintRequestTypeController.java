package af.gov.anar.ebreshna.csc.complaint.complaint_request_type;

import af.gov.anar.api.handler.ResponseHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/csc/complaint-request-types")
public class ComplaintRequestTypeController extends ResponseHandler {
}
