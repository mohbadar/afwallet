package af.gov.anar.ebreshna.common.base.province;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/administration/provinces")
public class ProvinceController {


    @Autowired
    private ProvinceService service;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<Province>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Province> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }


    @GetMapping(value = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Province> findByName(@PathVariable(name = "name", required = true) String name)
    {
        return ResponseEntity.ok(service.findByName(name));
    }

    @GetMapping(value = "/code/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Province> findByCode(@PathVariable(name = "code", required = true) String code)
    {
        return ResponseEntity.ok(service.findByProvinceCode(code));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Province> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) Province obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Province> save(@RequestBody(required = true) Province obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}
