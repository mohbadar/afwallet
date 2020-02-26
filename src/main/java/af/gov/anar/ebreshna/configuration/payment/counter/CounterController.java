package af.gov.anar.ebreshna.configuration.payment.counter;

import af.gov.anar.api.handler.ResponseHandler;
import af.gov.anar.ebreshna.configuration.payment.counter.CounterMaster;
import af.gov.anar.ebreshna.configuration.payment.counter.CounterService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/payment/counters")
public class CounterController extends ResponseHandler {

    @Autowired
    private CounterService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<CounterMaster>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<CounterMaster> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<CounterMaster> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) CounterMaster obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<CounterMaster> save(@RequestBody(required = true) CounterMaster obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }

}
