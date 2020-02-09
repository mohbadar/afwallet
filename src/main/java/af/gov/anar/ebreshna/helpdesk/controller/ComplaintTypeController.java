package af.gov.anar.ebreshna.helpdesk.controller;

import af.gov.anar.ebreshna.helpdesk.model.Complaint;
import af.gov.anar.ebreshna.helpdesk.model.ComplaintType;
import af.gov.anar.ebreshna.helpdesk.service.ComplaintTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/helpdesk/complainttypes")
public class ComplaintTypeController {

    @Autowired
    private ComplaintTypeService service;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<ComplaintType>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ComplaintType> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ComplaintType> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) ComplaintType obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ComplaintType> save(@RequestBody(required = true) ComplaintType obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}
