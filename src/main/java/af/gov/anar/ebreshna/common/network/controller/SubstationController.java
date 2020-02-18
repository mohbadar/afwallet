package af.gov.anar.ebreshna.common.network.controller;

import af.gov.anar.ebreshna.common.network.model.SubstationMaster;
import af.gov.anar.ebreshna.common.network.service.SubstationMasterService;
import af.gov.anar.ebreshna.common.office.model.DesignationMaster;
import af.gov.anar.ebreshna.common.office.service.DesignationMasterService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/network/substations")
public class SubstationController {

    @Autowired
    private SubstationMasterService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<SubstationMaster>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<SubstationMaster> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<SubstationMaster> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) SubstationMaster obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<SubstationMaster> save(@RequestBody(required = true) SubstationMaster obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}
