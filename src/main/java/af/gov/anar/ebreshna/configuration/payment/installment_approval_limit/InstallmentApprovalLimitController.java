package af.gov.anar.ebreshna.configuration.payment.installment_approval_limit;

import af.gov.anar.api.handler.ResponseHandler;
import af.gov.anar.ebreshna.configuration.payment.installment_approval_limit.InstallmentApprovalLimit;
import af.gov.anar.ebreshna.configuration.payment.installment_approval_limit.InstallmentApprovalLimitService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/payment/installment-approval-limits")
public class InstallmentApprovalLimitController extends ResponseHandler {

    @Autowired
    private InstallmentApprovalLimitService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<InstallmentApprovalLimit>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<InstallmentApprovalLimit> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<InstallmentApprovalLimit> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) InstallmentApprovalLimit obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<InstallmentApprovalLimit> save(@RequestBody(required = true) InstallmentApprovalLimit obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}
