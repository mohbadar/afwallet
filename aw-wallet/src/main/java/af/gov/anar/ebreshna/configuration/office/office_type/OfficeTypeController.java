package af.gov.anar.ebreshna.configuration.office.office_type;

import af.gov.anar.ebreshna.configuration.office.office_type.OfficeType;
import af.gov.anar.ebreshna.configuration.office.office_type.OfficeTypeService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/config/office/office-type")
public class OfficeTypeController {

    @Autowired
    private OfficeTypeService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<OfficeType>> findAll()
    {
        return ResponseEntity.ok(service.findAll());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<OfficeType> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<OfficeType> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) OfficeType obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<OfficeType> save(@Valid  @RequestBody(required = true) OfficeType obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}
