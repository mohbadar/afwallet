package af.gov.anar.ebreshna.configuration.nsc.supply_voltage;

import af.gov.anar.ebreshna.configuration.nsc.supply_voltage.SupplyVoltage;
import af.gov.anar.ebreshna.configuration.nsc.supply_voltage.SupplyVoltageService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/nsc/supplyvoltages")
public class SupplyVoltageController {

    @Autowired
    private SupplyVoltageService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<SupplyVoltage>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<SupplyVoltage> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<SupplyVoltage> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) SupplyVoltage obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<SupplyVoltage> save(@RequestBody(required = true) SupplyVoltage obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}