package af.gov.anar.ebreshna.csc.complaint.complaint_request;

import af.gov.anar.api.handler.ResponseHandler;
import af.gov.anar.ebreshna.helpdesk.model.Complaint;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import af.gov.anar.ebreshna.nsc.applicant.Applicant;
import af.gov.anar.ebreshna.nsc.applicant.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/csc/complaint-requests")
public class ComplaintRequestController extends ResponseHandler {

    @Autowired
    private ComplaintRequestService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<ComplaintRequest>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ComplaintRequest> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ComplaintRequest> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) ComplaintRequest obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ComplaintRequest> save(@RequestBody(required = true) ComplaintRequest obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }

}
