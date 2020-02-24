package af.gov.anar.ebreshna.configuration.common.fee.controller;

import af.gov.anar.api.handler.ResponseHandler;
import af.gov.anar.ebreshna.configuration.common.fee.model.FeeType;
import af.gov.anar.ebreshna.configuration.common.fee.service.FeeTypeService;
import af.gov.anar.ebreshna.configuration.common.workingcalender.model.WorkingCalenderTemplate;
import af.gov.anar.ebreshna.configuration.common.workingcalender.service.WorkingCalenderTemplateService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/common/feetypes")
public class FeeTypeController extends ResponseHandler {

    @Autowired
    private FeeTypeService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<FeeType>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<FeeType> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<FeeType> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) FeeType obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<FeeType> save(@RequestBody(required = true) FeeType obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}
