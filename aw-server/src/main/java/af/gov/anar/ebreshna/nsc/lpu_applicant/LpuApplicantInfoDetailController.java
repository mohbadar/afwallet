package af.gov.anar.ebreshna.nsc.lpu_applicant;

import af.gov.anar.api.handler.ResponseHandler;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import af.gov.anar.ebreshna.nsc.lpu_applicant.LpuApplicantInfoDetail;
import af.gov.anar.ebreshna.nsc.lpu_applicant.LpuApplicantInfoDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/nsc/lpu-applicants")
public class LpuApplicantInfoDetailController extends ResponseHandler {

    @Autowired
    private LpuApplicantInfoDetailService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<LpuApplicantInfoDetail>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<LpuApplicantInfoDetail> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<LpuApplicantInfoDetail> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) LpuApplicantInfoDetail obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<LpuApplicantInfoDetail> save(@RequestBody(required = true) LpuApplicantInfoDetail obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}
