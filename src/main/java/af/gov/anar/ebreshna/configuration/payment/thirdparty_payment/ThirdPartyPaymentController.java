package af.gov.anar.ebreshna.configuration.payment.thirdparty_payment;

import af.gov.anar.api.handler.ResponseHandler;
import af.gov.anar.ebreshna.configuration.payment.thirdparty_payment.ThirdPartyPayment;
import af.gov.anar.ebreshna.configuration.payment.thirdparty_payment.ThirdPartyPaymentService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/payment/third-party-payments")
public class ThirdPartyPaymentController extends ResponseHandler {

    @Autowired
    private ThirdPartyPaymentService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<ThirdPartyPayment>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ThirdPartyPayment> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ThirdPartyPayment> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) ThirdPartyPayment obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ThirdPartyPayment> save(@RequestBody(required = true) ThirdPartyPayment obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }

}
