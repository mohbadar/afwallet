package af.gov.anar.ebreshna.configuration.metering.activity_relation;

import af.gov.anar.api.handler.ResponseHandler;
import af.gov.anar.ebreshna.configuration.metering.activity_relation.ActivityRelation;
import af.gov.anar.ebreshna.configuration.metering.activity_relation.ActivityRelationService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/metering/activity-relations")
public class ActivityRelationController extends ResponseHandler {


    @Autowired
    private ActivityRelationService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<ActivityRelation>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ActivityRelation> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ActivityRelation> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) ActivityRelation obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ActivityRelation> save(@RequestBody(required = true) ActivityRelation obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }

}
