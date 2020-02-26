package af.gov.anar.ebreshna.configuration.metering.controller;

import af.gov.anar.api.handler.ResponseHandler;
import af.gov.anar.ebreshna.configuration.metering.model.CycleConfiguration;
import af.gov.anar.ebreshna.configuration.metering.model.CycleConfiguration;
import af.gov.anar.ebreshna.configuration.metering.service.CycleService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/metering/cycle-configurations")
public class CycleConfigurationController extends ResponseHandler {

    @Autowired
    private CycleService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<CycleConfiguration>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<CycleConfiguration> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<CycleConfiguration> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) CycleConfiguration obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<CycleConfiguration> save(@RequestBody(required = true) CycleConfiguration obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}
