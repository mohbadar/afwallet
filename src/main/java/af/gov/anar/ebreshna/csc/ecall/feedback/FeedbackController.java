package af.gov.anar.ebreshna.csc.ecall.feedback;

import af.gov.anar.api.handler.ResponseHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/csc/ecall/feedbacks")
public class FeedbackController extends ResponseHandler {
}
