package af.gov.anar.ebreshna.configuration.office.controller;

import af.gov.anar.ebreshna.configuration.office.field_staff.FieldStaff;
import af.gov.anar.ebreshna.configuration.office.field_staff.FieldStaffService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/office/fieldstaff")
public class FieldStaffController {

    @Autowired
    private FieldStaffService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<FieldStaff>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<FieldStaff> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<FieldStaff> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) FieldStaff obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<FieldStaff> save(@RequestBody(required = true) FieldStaff obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}
