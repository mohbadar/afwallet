package af.gov.anar.ebreshna.csc.ecall.ivr_call;

import af.gov.anar.api.handler.ResponseHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/csc/ecall/ivr-calls")
public class IvrCallController extends ResponseHandler {
}
