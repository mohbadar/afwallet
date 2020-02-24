package af.gov.anar.ebreshna.configuration.payment.controller;

import af.gov.anar.api.handler.ResponseHandler;
import af.gov.anar.ebreshna.configuration.payment.model.BankBranch;
import af.gov.anar.ebreshna.configuration.payment.model.InstallmentPenalty;
import af.gov.anar.ebreshna.configuration.payment.service.BankBranchService;
import af.gov.anar.ebreshna.configuration.payment.service.InstallmentPenaltyService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/payment/installment-penalties")
public class InstallmentPenaltyController extends ResponseHandler {
    @Autowired
    private InstallmentPenaltyService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<InstallmentPenalty>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<InstallmentPenalty> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<InstallmentPenalty> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) InstallmentPenalty obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<InstallmentPenalty> save(@RequestBody(required = true) InstallmentPenalty obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}
