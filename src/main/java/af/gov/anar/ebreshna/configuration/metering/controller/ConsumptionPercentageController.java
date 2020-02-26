package af.gov.anar.ebreshna.configuration.metering.controller;

import af.gov.anar.api.handler.ResponseHandler;
import af.gov.anar.ebreshna.configuration.metering.model.ConsumptionPercentage;
import af.gov.anar.ebreshna.configuration.metering.service.ConsumptionPercentageService;
import af.gov.anar.ebreshna.configuration.network.model.AreaMaster;
import af.gov.anar.ebreshna.configuration.network.service.AreaMasterService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/metering/consumption-percentages")
public class ConsumptionPercentageController extends ResponseHandler {

    @Autowired
    private ConsumptionPercentageService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<ConsumptionPercentage>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ConsumptionPercentage> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ConsumptionPercentage> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) ConsumptionPercentage obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ConsumptionPercentage> save(@RequestBody(required = true) ConsumptionPercentage obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }

}
