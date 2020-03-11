package af.gov.anar.ebreshna.configuration.billing.voltage_unit;

import af.gov.anar.api.handler.ResponseHandler;
import af.gov.anar.ebreshna.configuration.billing.voltage_unit.VoltageUnit;
import af.gov.anar.ebreshna.configuration.billing.voltage_unit.VoltageUnitService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/billing/voltage-units")
public class VoltageUnitController extends ResponseHandler {

    @Autowired
    private VoltageUnitService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<VoltageUnit>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<VoltageUnit> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<VoltageUnit> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) VoltageUnit obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<VoltageUnit> save(@RequestBody(required = true) VoltageUnit obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}
