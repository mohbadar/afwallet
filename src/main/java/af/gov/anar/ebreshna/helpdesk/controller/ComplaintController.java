package af.gov.anar.ebreshna.helpdesk.controller;

import af.gov.anar.ebreshna.helpdesk.model.Complaint;
import af.gov.anar.ebreshna.helpdesk.service.ComplaintService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/helpdesk/complaints")
public class ComplaintController {

    @Autowired
    private ComplaintService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<Complaint>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<Complaint>> findByAssignee()
    {
        return ResponseEntity.ok(service.findByAssignee(userService.getId()));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Complaint> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Complaint> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) Complaint obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Complaint> save(@RequestBody(required = true) Complaint obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}
