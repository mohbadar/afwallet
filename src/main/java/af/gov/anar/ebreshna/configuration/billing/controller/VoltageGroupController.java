package af.gov.anar.ebreshna.configuration.billing.controller;

import af.gov.anar.api.handler.ResponseHandler;
import af.gov.anar.ebreshna.configuration.billing.enumeration.VoltageGroup;
import af.gov.anar.ebreshna.configuration.billing.model.CustomerGroupMaster;
import af.gov.anar.ebreshna.configuration.billing.model.VoltageGroupMaster;
import af.gov.anar.ebreshna.configuration.billing.service.CustomerGroupService;
import af.gov.anar.ebreshna.configuration.billing.service.VoltageGroupService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/billing/voltage-groups")
public class VoltageGroupController extends ResponseHandler {

    @Autowired
    private VoltageGroupService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<VoltageGroupMaster>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<VoltageGroupMaster> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<VoltageGroupMaster> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) VoltageGroupMaster obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<VoltageGroupMaster> save(@RequestBody(required = true) VoltageGroupMaster obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}
