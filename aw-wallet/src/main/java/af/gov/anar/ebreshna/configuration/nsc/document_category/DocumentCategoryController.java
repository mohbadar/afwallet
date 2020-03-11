package af.gov.anar.ebreshna.configuration.nsc.document_category;

import af.gov.anar.ebreshna.configuration.nsc.document_category.DocumentCategoryMaster;
import af.gov.anar.ebreshna.configuration.nsc.document_category.DocumentCategoryService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/nsc/documentcatgories")
public class DocumentCategoryController {

    @Autowired
    private DocumentCategoryService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<DocumentCategoryMaster>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<DocumentCategoryMaster> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<DocumentCategoryMaster> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) DocumentCategoryMaster obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<DocumentCategoryMaster> save(@RequestBody(required = true) DocumentCategoryMaster obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}
