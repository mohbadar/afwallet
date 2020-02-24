package af.gov.anar.ebreshna.configuration.nsc.controller;

import af.gov.anar.ebreshna.configuration.nsc.model.MaterialEstimateMaster;
import af.gov.anar.ebreshna.configuration.nsc.service.MaterialEstimateService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/nsc/materialestimates")
public class MaterialEstimateController {

    @Autowired
    private MaterialEstimateService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<MaterialEstimateMaster>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<MaterialEstimateMaster> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<MaterialEstimateMaster> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) MaterialEstimateMaster obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<MaterialEstimateMaster> save(@RequestBody(required = true) MaterialEstimateMaster obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}
