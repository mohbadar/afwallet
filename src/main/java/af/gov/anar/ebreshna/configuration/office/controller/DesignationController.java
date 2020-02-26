package af.gov.anar.ebreshna.configuration.office.controller;


import af.gov.anar.ebreshna.configuration.office.designation.DesignationMaster;
import af.gov.anar.ebreshna.configuration.office.designation.DesignationMasterService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/office/designations")
public class DesignationController {

    @Autowired
    private DesignationMasterService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<DesignationMaster>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<DesignationMaster> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<DesignationMaster> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) DesignationMaster obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<DesignationMaster> save(@RequestBody(required = true) DesignationMaster obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}
