package af.gov.anar.ebreshna.configuration.common.workingcalender.controller;

import af.gov.anar.ebreshna.configuration.common.workingcalender.model.WorkingCalenderTemplate;
import af.gov.anar.ebreshna.configuration.common.workingcalender.service.WorkingCalenderTemplateService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = { "/api/working-calender-template", })
@Slf4j
public class WorkingCalenderTemplateController {

    @Autowired
    private WorkingCalenderTemplateService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<WorkingCalenderTemplate>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<WorkingCalenderTemplate> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<WorkingCalenderTemplate> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) WorkingCalenderTemplate obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<WorkingCalenderTemplate> save(@RequestBody(required = true) WorkingCalenderTemplate obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}
