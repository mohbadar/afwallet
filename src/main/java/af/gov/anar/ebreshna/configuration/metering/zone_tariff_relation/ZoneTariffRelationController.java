package af.gov.anar.ebreshna.configuration.metering.zone_tariff_relation;

import af.gov.anar.ebreshna.configuration.common.BaseEntity;
import af.gov.anar.ebreshna.configuration.metering.zone_tariff_relation.ZoneTariffRelation;
import af.gov.anar.ebreshna.configuration.metering.zone_tariff_relation.ZoneTariffRelationService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/metering/zone-tariff-relations")
public class ZoneTariffRelationController extends BaseEntity {

    @Autowired
    private ZoneTariffRelationService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<ZoneTariffRelation>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ZoneTariffRelation> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ZoneTariffRelation> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) ZoneTariffRelation obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ZoneTariffRelation> save(@RequestBody(required = true) ZoneTariffRelation obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}
