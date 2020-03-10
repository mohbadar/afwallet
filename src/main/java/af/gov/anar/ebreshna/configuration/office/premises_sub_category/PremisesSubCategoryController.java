package af.gov.anar.ebreshna.configuration.office.premises_sub_category;

import af.gov.anar.ebreshna.configuration.office.premises_sub_category.PremisesSubCategory;
import af.gov.anar.ebreshna.configuration.office.premises_sub_category.PremisesSubCategoryService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/office/premises-sub-categories")
public class PremisesSubCategoryController {

    @Autowired
    private PremisesSubCategoryService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<PremisesSubCategory>> findAll()
    {
        return ResponseEntity.ok(service.findAll());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<PremisesSubCategory> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<PremisesSubCategory> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) PremisesSubCategory obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<PremisesSubCategory> save(@RequestBody(required = true) PremisesSubCategory obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}
