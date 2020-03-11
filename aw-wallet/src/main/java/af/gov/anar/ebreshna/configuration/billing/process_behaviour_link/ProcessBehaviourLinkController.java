package af.gov.anar.ebreshna.configuration.billing.process_behaviour_link;

import af.gov.anar.api.handler.ResponseHandler;
import af.gov.anar.ebreshna.configuration.billing.process_behaviour_link.ProcessBehaviourLinkConfiguration;
import af.gov.anar.ebreshna.configuration.billing.process_behaviour_link.ProcessBehaviourLinkService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/billing/process-behaviour-link")
public class ProcessBehaviourLinkController extends ResponseHandler {

    @Autowired
    private ProcessBehaviourLinkService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<ProcessBehaviourLinkConfiguration>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ProcessBehaviourLinkConfiguration> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ProcessBehaviourLinkConfiguration> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) ProcessBehaviourLinkConfiguration obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ProcessBehaviourLinkConfiguration> save(@RequestBody(required = true) ProcessBehaviourLinkConfiguration obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}
