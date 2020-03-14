package af.gov.anar.ebreshna.nsc.communication_address;

import af.gov.anar.api.handler.ResponseHandler;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import af.gov.anar.ebreshna.nsc.communication_address.CommunicationAddress;
import af.gov.anar.ebreshna.nsc.communication_address.CommunicationAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/nsc/applicant-communication-addresses")
public class CommunicationAddressController extends ResponseHandler {

    @Autowired
    private CommunicationAddressService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<CommunicationAddress>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<CommunicationAddress> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<CommunicationAddress> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) CommunicationAddress obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<CommunicationAddress> save(@RequestBody(required = true) CommunicationAddress obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}