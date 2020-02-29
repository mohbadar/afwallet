package af.gov.anar.ebreshna.csc.complaint.complaint_request_type;

import af.gov.anar.api.handler.ResponseHandler;
import af.gov.anar.ebreshna.csc.complaint.complaint_request.ComplaintRequest;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import af.gov.anar.ebreshna.nsc.applicant.Applicant;
import af.gov.anar.ebreshna.nsc.applicant.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/csc/complaint-request-types")
public class ComplaintRequestTypeController extends ResponseHandler {

    @Autowired
    private ComplaintRequestTypeService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<ComplaintRequestType>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ComplaintRequestType> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ComplaintRequestType> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) ComplaintRequestType obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ComplaintRequestType> save(@RequestBody(required = true) ComplaintRequestType obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}
