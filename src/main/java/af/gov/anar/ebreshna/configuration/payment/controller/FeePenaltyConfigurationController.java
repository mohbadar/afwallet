package af.gov.anar.ebreshna.configuration.payment.controller;

import af.gov.anar.api.handler.ResponseHandler;
import af.gov.anar.ebreshna.configuration.payment.model.BankBranch;
import af.gov.anar.ebreshna.configuration.payment.model.FeePenaltyConfiguration;
import af.gov.anar.ebreshna.configuration.payment.service.BankBranchService;
import af.gov.anar.ebreshna.configuration.payment.service.FeePenaltyService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/payment/fee-penalty-configurations")
public class FeePenaltyConfigurationController extends ResponseHandler {

    @Autowired
    private FeePenaltyService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<FeePenaltyConfiguration>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<FeePenaltyConfiguration> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<FeePenaltyConfiguration> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) FeePenaltyConfiguration obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<FeePenaltyConfiguration> save(@RequestBody(required = true) FeePenaltyConfiguration obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}
