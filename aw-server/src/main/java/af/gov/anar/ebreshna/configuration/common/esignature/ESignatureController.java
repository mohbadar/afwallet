package af.gov.anar.ebreshna.configuration.common.esignature;

import af.gov.anar.api.handler.ResponseHandler;
import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.configuration.common.fee.model.FeeType;
import af.gov.anar.ebreshna.configuration.common.fee.service.FeeTypeService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/common/e-signatures")
public class ESignatureController extends ResponseHandler {

    @Autowired
    private ESignatureService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<ESignature>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ESignature> findByLoggedInUser()
    {
        return ResponseEntity.ok(service.findByLoggedInUser());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ESignature> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ESignature> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) ESignature obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ESignature> save(@RequestBody(required = true) ESignature obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }

}
