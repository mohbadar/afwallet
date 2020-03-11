package af.gov.anar.ebreshna.nsc.applicant_document;

import af.gov.anar.api.handler.ResponseHandler;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import af.gov.anar.ebreshna.nsc.applicant_document.ApplicantDocument;
import af.gov.anar.ebreshna.nsc.applicant_document.ApplicantDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/nsc/applicant-documents")
public class ApplicantDocumentController extends ResponseHandler {

    @Autowired
    private ApplicantDocumentService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<ApplicantDocument>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ApplicantDocument> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ApplicantDocument> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) ApplicantDocument obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ApplicantDocument> save(@RequestBody(required = true) ApplicantDocument obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}
