package af.gov.anar.ebreshna.configuration.billing.controller;

import af.gov.anar.api.handler.ResponseHandler;
import af.gov.anar.ebreshna.configuration.billing.model.BehaviourConfiguration;
import af.gov.anar.ebreshna.configuration.billing.service.BehaviourService;
import af.gov.anar.ebreshna.configuration.payment.model.BankBranch;
import af.gov.anar.ebreshna.configuration.payment.service.BankBranchService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/billing/behaviours")
public class BehaviourController extends ResponseHandler {

    @Autowired
    private BehaviourService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<BehaviourConfiguration>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<BehaviourConfiguration> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<BehaviourConfiguration> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) BehaviourConfiguration obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<BehaviourConfiguration> save(@RequestBody(required = true) BehaviourConfiguration obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }

}
