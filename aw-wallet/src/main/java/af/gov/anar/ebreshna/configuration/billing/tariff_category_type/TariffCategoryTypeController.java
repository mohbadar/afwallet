package af.gov.anar.ebreshna.configuration.billing.tariff_category_type;

import af.gov.anar.api.handler.ResponseHandler;
import af.gov.anar.ebreshna.configuration.billing.tariff_category_type.TariffCategoryTypeMaster;
import af.gov.anar.ebreshna.configuration.billing.tariff_category_type.TariffCategoryTypeService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/billing/tariff-category-types")
public class TariffCategoryTypeController extends ResponseHandler {


    @Autowired
    private TariffCategoryTypeService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<TariffCategoryTypeMaster>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<TariffCategoryTypeMaster> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<TariffCategoryTypeMaster> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) TariffCategoryTypeMaster obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<TariffCategoryTypeMaster> save(@RequestBody(required = true) TariffCategoryTypeMaster obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}
