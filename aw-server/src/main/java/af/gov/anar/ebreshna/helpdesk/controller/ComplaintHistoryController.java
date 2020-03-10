package af.gov.anar.ebreshna.helpdesk.controller;

import af.gov.anar.ebreshna.helpdesk.model.Complaint;
import af.gov.anar.ebreshna.helpdesk.model.ComplaintHistory;
import af.gov.anar.ebreshna.helpdesk.service.ComplaintHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/helpdesk/complainthistories")
public class ComplaintHistoryController {

    @Autowired
    private ComplaintHistoryService service;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<ComplaintHistory>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ComplaintHistory> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ComplaintHistory> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) ComplaintHistory obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ComplaintHistory> save(@RequestBody(required = true) ComplaintHistory obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}
