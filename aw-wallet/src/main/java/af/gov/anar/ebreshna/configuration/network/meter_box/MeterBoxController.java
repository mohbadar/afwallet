package af.gov.anar.ebreshna.configuration.network.meter_box;

import af.gov.anar.ebreshna.configuration.network.meter_box.MeterBoxMaster;
import af.gov.anar.ebreshna.configuration.network.meter_box.MeterBoxMasterService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/network/meterboxes")
public class MeterBoxController {

    @Autowired
    private MeterBoxMasterService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<MeterBoxMaster>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<MeterBoxMaster> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<MeterBoxMaster> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) MeterBoxMaster obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<MeterBoxMaster> save(@RequestBody(required = true) MeterBoxMaster obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}
