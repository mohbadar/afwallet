package af.gov.anar.ebreshna.configuration.payment.posting_days;

import af.gov.anar.api.handler.ResponseHandler;
import af.gov.anar.ebreshna.configuration.payment.posting_days.PostingDaysConfiguration;
import af.gov.anar.ebreshna.configuration.payment.posting_days.PostingDaysConfigurationService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/payment/posting-days")
public class PostingDaysConfigurationController extends ResponseHandler {

    @Autowired
    private PostingDaysConfigurationService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<PostingDaysConfiguration>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<PostingDaysConfiguration> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<PostingDaysConfiguration> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) PostingDaysConfiguration obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<PostingDaysConfiguration> save(@RequestBody(required = true) PostingDaysConfiguration obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}
