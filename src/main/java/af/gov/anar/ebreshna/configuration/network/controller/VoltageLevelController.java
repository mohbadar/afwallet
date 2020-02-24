package af.gov.anar.ebreshna.configuration.network.controller;

import af.gov.anar.ebreshna.configuration.network.model.VoltageLevel;
import af.gov.anar.ebreshna.configuration.network.service.VoltageLevelService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/network/voltagelevels")
public class VoltageLevelController {

    @Autowired
    private VoltageLevelService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<VoltageLevel>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<VoltageLevel> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<VoltageLevel> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) VoltageLevel obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<VoltageLevel> save(@RequestBody(required = true) VoltageLevel obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}
