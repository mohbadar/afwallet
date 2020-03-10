package af.gov.anar.ebreshna.configuration.nsc.unit_type;

import af.gov.anar.api.handler.ResponseHandler;
import af.gov.anar.ebreshna.configuration.nsc.unit_type.UnitType;
import af.gov.anar.ebreshna.configuration.nsc.unit_type.UnitTypeService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/nsc/unittypes")
public class UnitTypeController extends ResponseHandler {
    @Autowired
    private UnitTypeService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<UnitType>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<UnitType> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<UnitType> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) UnitType obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<UnitType> save(@RequestBody(required = true) UnitType obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}