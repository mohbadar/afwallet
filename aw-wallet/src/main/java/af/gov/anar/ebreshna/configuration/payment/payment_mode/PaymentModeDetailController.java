package af.gov.anar.ebreshna.configuration.payment.payment_mode;

import af.gov.anar.api.handler.ResponseHandler;
import af.gov.anar.ebreshna.configuration.payment.payment_mode.PaymentModeDetail;
import af.gov.anar.ebreshna.configuration.payment.payment_mode.PaymentModeDetailService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/payment/payment-modes")
public class PaymentModeDetailController extends ResponseHandler {

    @Autowired
    private PaymentModeDetailService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<PaymentModeDetail>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<PaymentModeDetail> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<PaymentModeDetail> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) PaymentModeDetail obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<PaymentModeDetail> save(@RequestBody(required = true) PaymentModeDetail obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}