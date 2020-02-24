package af.gov.anar.ebreshna.configuration.csc.controller;


import af.gov.anar.ebreshna.configuration.csc.model.ApprovalLimit;
import af.gov.anar.ebreshna.configuration.csc.service.ApprovalLimitService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/csc/approvallimits")
public class ApprovalLimitController {

    @Autowired
    private ApprovalLimitService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<ApprovalLimit>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ApprovalLimit> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ApprovalLimit> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) ApprovalLimit obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ApprovalLimit> save(@RequestBody(required = true) ApprovalLimit obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}
