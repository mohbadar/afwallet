package af.gov.anar.ebreshna.configuration.network.dtr_category;

import af.gov.anar.ebreshna.configuration.network.dtr_category.DtrCategory;
import af.gov.anar.ebreshna.configuration.network.dtr_category.DtrCategoryService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/network/dtrcategories")
public class DtrCategoryController {

    @Autowired
    private DtrCategoryService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<DtrCategory>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<DtrCategory> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<DtrCategory> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) DtrCategory obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<DtrCategory> save(@RequestBody(required = true) DtrCategory obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}
