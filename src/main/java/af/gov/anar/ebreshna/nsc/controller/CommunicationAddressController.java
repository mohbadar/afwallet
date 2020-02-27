package af.gov.anar.ebreshna.nsc.controller;

import af.gov.anar.api.handler.ResponseHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/nsc/applicant-communication-addresses")
public class CommunicationAddressController extends ResponseHandler {
}
