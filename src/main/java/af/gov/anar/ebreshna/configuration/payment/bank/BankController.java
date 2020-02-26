package af.gov.anar.ebreshna.configuration.payment.bank;

import af.gov.anar.api.handler.ResponseHandler;
import af.gov.anar.ebreshna.configuration.payment.bank.BankMaster;
import af.gov.anar.ebreshna.configuration.payment.bank.BankService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/payment/banks")
public class BankController extends ResponseHandler {

    @Autowired
    private BankService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<BankMaster>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<BankMaster> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<BankMaster> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) BankMaster obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<BankMaster> save(@RequestBody(required = true) BankMaster obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}
