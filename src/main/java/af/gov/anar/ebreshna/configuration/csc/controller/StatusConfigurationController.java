package af.gov.anar.ebreshna.configuration.csc.controller;

import af.gov.anar.ebreshna.configuration.csc.model.StatusConfiguration;
import af.gov.anar.ebreshna.configuration.csc.service.StatusConfigurationService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/csc/statusconfigs")
public class StatusConfigurationController {


    @Autowired
    private StatusConfigurationService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<StatusConfiguration>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<StatusConfiguration> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<StatusConfiguration> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) StatusConfiguration obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<StatusConfiguration> save(@RequestBody(required = true) StatusConfiguration obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}
