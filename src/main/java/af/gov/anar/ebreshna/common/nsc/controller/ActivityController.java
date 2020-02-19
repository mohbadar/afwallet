package af.gov.anar.ebreshna.common.nsc.controller;

import af.gov.anar.api.handler.ResponseHandler;
import af.gov.anar.ebreshna.common.nsc.model.ActivityMaster;
import af.gov.anar.ebreshna.common.nsc.service.ActivityService;
import af.gov.anar.ebreshna.common.office.model.DesignationMaster;
import af.gov.anar.ebreshna.common.office.service.DesignationMasterService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/nsc/activities")
public class ActivityController extends ResponseHandler {
    @Autowired
    private ActivityService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<ActivityMaster>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ActivityMaster> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ActivityMaster> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) ActivityMaster obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ActivityMaster> save(@RequestBody(required = true) ActivityMaster obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }

}
