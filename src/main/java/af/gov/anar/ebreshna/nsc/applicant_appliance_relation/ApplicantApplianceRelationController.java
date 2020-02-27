package af.gov.anar.ebreshna.nsc.applicant_appliance_relation;

import af.gov.anar.api.handler.ResponseHandler;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import af.gov.anar.ebreshna.nsc.applicant_appliance_relation.ApplicantApplianceRelation;
import af.gov.anar.ebreshna.nsc.applicant_appliance_relation.ApplicantApplianceRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/nsc/applicant-appliance-relations")
public class ApplicantApplianceRelationController extends ResponseHandler {

    @Autowired
    private ApplicantApplianceRelationService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<ApplicantApplianceRelation>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ApplicantApplianceRelation> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ApplicantApplianceRelation> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) ApplicantApplianceRelation obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ApplicantApplianceRelation> save(@RequestBody(required = true) ApplicantApplianceRelation obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }

}
