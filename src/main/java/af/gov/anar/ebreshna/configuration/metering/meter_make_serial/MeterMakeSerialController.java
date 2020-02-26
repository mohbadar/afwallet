package af.gov.anar.ebreshna.configuration.metering.meter_make_serial;

import af.gov.anar.api.handler.ResponseHandler;
import af.gov.anar.ebreshna.configuration.metering.meter_make_serial.MeterMakeSerial;
import af.gov.anar.ebreshna.configuration.metering.meter_make_serial.MeterMakeSerialService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/metering/meter-make-serials")
public class MeterMakeSerialController extends ResponseHandler {

    @Autowired
    private MeterMakeSerialService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<MeterMakeSerial>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<MeterMakeSerial> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<MeterMakeSerial> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) MeterMakeSerial obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<MeterMakeSerial> save(@RequestBody(required = true) MeterMakeSerial obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }

}
