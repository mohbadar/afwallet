package af.gov.anar.ebreshna.configuration.billing.controller;

import af.gov.anar.api.handler.ResponseHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/config/billing/tariff-categories")
public class TariffCategoryController extends ResponseHandler {
}
