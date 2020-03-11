package af.gov.anar.ebreshna.configuration.metering.zone_cycle_relation;

import af.gov.anar.api.handler.ResponseHandler;
import af.gov.anar.ebreshna.configuration.metering.zone_cycle_relation.ZoneCycleRelation;
import af.gov.anar.ebreshna.configuration.metering.zone_cycle_relation.ZoneCycleRelationService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/metering/zone-cycle-relations")
public class ZoneCycleRelationController extends ResponseHandler {

    @Autowired
    private ZoneCycleRelationService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<ZoneCycleRelation>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ZoneCycleRelation> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ZoneCycleRelation> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) ZoneCycleRelation obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ZoneCycleRelation> save(@RequestBody(required = true) ZoneCycleRelation obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}
