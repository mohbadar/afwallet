package af.gov.anar.ebreshna.configuration.metering.controller;

import af.gov.anar.api.handler.ResponseHandler;

import af.gov.anar.ebreshna.configuration.metering.model.CycleConfiguration;
import af.gov.anar.ebreshna.configuration.metering.model.MeterCapacityMaster;
import af.gov.anar.ebreshna.configuration.metering.service.CycleService;
import af.gov.anar.ebreshna.configuration.metering.service.MeterCapacityService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/metering/meter-capacities")
public class MeterCapacityController extends ResponseHandler {

    @Autowired
    private MeterCapacityService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<MeterCapacityMaster>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<MeterCapacityMaster> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<MeterCapacityMaster> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) MeterCapacityMaster obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<MeterCapacityMaster> save(@RequestBody(required = true) MeterCapacityMaster obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }

}
