package af.gov.anar.ebreshna.configuration.network.controller;

import af.gov.anar.ebreshna.configuration.network.model.MeterReaderMaster;
import af.gov.anar.ebreshna.configuration.network.service.MeterReaderMasterService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/network/meterreaders")
public class MeterReaderController {

    @Autowired
    private MeterReaderMasterService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<MeterReaderMaster>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<MeterReaderMaster> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<MeterReaderMaster> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) MeterReaderMaster obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<MeterReaderMaster> save(@RequestBody(required = true) MeterReaderMaster obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}
