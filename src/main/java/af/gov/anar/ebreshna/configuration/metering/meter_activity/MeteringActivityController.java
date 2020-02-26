package af.gov.anar.ebreshna.configuration.metering.meter_activity;

import af.gov.anar.api.handler.ResponseHandler;
import af.gov.anar.ebreshna.configuration.metering.meter_activity.MeteringActivityMaster;
import af.gov.anar.ebreshna.configuration.metering.meter_activity.ActivityService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/metering/metering-activities")
public class MeteringActivityController extends ResponseHandler {

    @Autowired
    private ActivityService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<MeteringActivityMaster>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<MeteringActivityMaster> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<MeteringActivityMaster> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) MeteringActivityMaster obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<MeteringActivityMaster> save(@RequestBody(required = true) MeteringActivityMaster obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }

}
