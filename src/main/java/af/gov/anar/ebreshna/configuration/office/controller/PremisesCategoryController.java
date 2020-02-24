package af.gov.anar.ebreshna.configuration.office.controller;

import af.gov.anar.ebreshna.configuration.office.model.PremisesCategory;
import af.gov.anar.ebreshna.configuration.office.service.PremisesCategoryService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/office/premisescategories")
public class PremisesCategoryController {

    @Autowired
    private PremisesCategoryService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<PremisesCategory>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<PremisesCategory> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<PremisesCategory> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) PremisesCategory obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<PremisesCategory> save(@RequestBody(required = true) PremisesCategory obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}
