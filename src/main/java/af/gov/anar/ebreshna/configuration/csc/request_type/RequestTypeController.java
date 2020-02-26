package af.gov.anar.ebreshna.configuration.csc.request_type;

import af.gov.anar.ebreshna.configuration.csc.request_type.RequestType;
import af.gov.anar.ebreshna.configuration.csc.request_type.RequestTypeService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/csc/requesttypes")
public class RequestTypeController {

    @Autowired
    private RequestTypeService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<RequestType>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<RequestType> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<RequestType> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) RequestType obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<RequestType> save(@RequestBody(required = true) RequestType obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}
