package af.gov.anar.ebreshna.common.nsc.controller;

import af.gov.anar.api.handler.ResponseHandler;
import af.gov.anar.ebreshna.common.nsc.model.DocumentMaster;
import af.gov.anar.ebreshna.common.nsc.model.UnitMaster;
import af.gov.anar.ebreshna.common.nsc.service.DocumentService;
import af.gov.anar.ebreshna.common.nsc.service.UnitService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/nsc/units")
public class UnitController extends ResponseHandler {

    @Autowired
    private UnitService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<UnitMaster>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<UnitMaster> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<UnitMaster> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) UnitMaster obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<UnitMaster> save(@RequestBody(required = true) UnitMaster obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}
