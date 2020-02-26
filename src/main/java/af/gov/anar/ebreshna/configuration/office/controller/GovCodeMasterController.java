package af.gov.anar.ebreshna.configuration.office.controller;

import af.gov.anar.api.handler.ResponseHandler;
import af.gov.anar.ebreshna.configuration.office.govt_code.GovCodeMaster;
import af.gov.anar.ebreshna.configuration.office.govt_code.GovCodeMasterService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/office/govcodes")
public class GovCodeMasterController extends ResponseHandler {

    @Autowired
    private GovCodeMasterService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<GovCodeMaster>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<GovCodeMaster> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<GovCodeMaster> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) GovCodeMaster obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<GovCodeMaster> save(@RequestBody(required = true) GovCodeMaster obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}
