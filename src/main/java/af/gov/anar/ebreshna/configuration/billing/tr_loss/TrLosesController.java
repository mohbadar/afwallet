package af.gov.anar.ebreshna.configuration.billing.tr_loss;

import af.gov.anar.api.handler.ResponseHandler;
import af.gov.anar.ebreshna.configuration.billing.tr_loss.TrLossesConfiguration;
import af.gov.anar.ebreshna.configuration.billing.tr_loss.TrLosesService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/billing/tr-losses")
public class TrLosesController extends ResponseHandler {

    @Autowired
    private TrLosesService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<TrLossesConfiguration>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<TrLossesConfiguration> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<TrLossesConfiguration> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) TrLossesConfiguration obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<TrLossesConfiguration> save(@RequestBody(required = true) TrLossesConfiguration obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}
