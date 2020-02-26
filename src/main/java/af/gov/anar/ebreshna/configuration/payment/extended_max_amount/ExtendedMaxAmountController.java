package af.gov.anar.ebreshna.configuration.payment.extended_max_amount;

import af.gov.anar.api.handler.ResponseHandler;
import af.gov.anar.ebreshna.configuration.payment.extended_max_amount.ExtendedMaxAmount;
import af.gov.anar.ebreshna.configuration.payment.extended_max_amount.ExtendedMaxAmountService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/payment/extend-max-amounts")
public class ExtendedMaxAmountController extends ResponseHandler {

    @Autowired
    private ExtendedMaxAmountService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<ExtendedMaxAmount>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ExtendedMaxAmount> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ExtendedMaxAmount> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) ExtendedMaxAmount obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ExtendedMaxAmount> save(@RequestBody(required = true) ExtendedMaxAmount obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}
