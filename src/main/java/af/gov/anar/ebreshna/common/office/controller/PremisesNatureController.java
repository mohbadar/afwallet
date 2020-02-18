package af.gov.anar.ebreshna.common.office.controller;

import af.gov.anar.ebreshna.common.office.model.OfficeType;
import af.gov.anar.ebreshna.common.office.model.PremisesNature;
import af.gov.anar.ebreshna.common.office.service.OfficeTypeService;
import af.gov.anar.ebreshna.common.office.service.PremisesNatureService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/office/premisesnatures")
public class PremisesNatureController {

    @Autowired
    private PremisesNatureService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<PremisesNature>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<PremisesNature> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<PremisesNature> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) PremisesNature obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<PremisesNature> save(@RequestBody(required = true) PremisesNature obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}
