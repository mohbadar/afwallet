package af.gov.anar.ebreshna.configuration.metering.meter_status;

import af.gov.anar.api.handler.ResponseHandler;
import af.gov.anar.ebreshna.configuration.metering.meter_status.MeterStatusMaster;
import af.gov.anar.ebreshna.configuration.metering.meter_status.MeterStatusService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/metering/meter-statuses")
public class MeterStatusController extends ResponseHandler {

    @Autowired
    private MeterStatusService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<MeterStatusMaster>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<MeterStatusMaster> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<MeterStatusMaster> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) MeterStatusMaster obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<MeterStatusMaster> save(@RequestBody(required = true) MeterStatusMaster obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }

}