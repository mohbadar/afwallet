package af.gov.anar.ebreshna.configuration.billing.controller;

import af.gov.anar.api.handler.ResponseHandler;
import af.gov.anar.ebreshna.configuration.billing.model.CustomerGroupMaster;
import af.gov.anar.ebreshna.configuration.billing.model.TariffCharge;
import af.gov.anar.ebreshna.configuration.billing.model.TariffChargeDetail;
import af.gov.anar.ebreshna.configuration.billing.service.CustomerGroupService;
import af.gov.anar.ebreshna.configuration.billing.service.TariffChargeDetailService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/billing/tariff-charge-details")
public class TariffChargeDetailController extends ResponseHandler {

    @Autowired
    private TariffChargeDetailService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<TariffChargeDetail>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<TariffChargeDetail> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<TariffChargeDetail> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) TariffChargeDetail obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<TariffChargeDetail> save(@RequestBody(required = true) TariffChargeDetail obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }

}
