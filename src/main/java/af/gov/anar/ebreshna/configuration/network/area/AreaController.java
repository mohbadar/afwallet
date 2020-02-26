package af.gov.anar.ebreshna.configuration.network.area;

import af.gov.anar.ebreshna.configuration.network.area.AreaMaster;
import af.gov.anar.ebreshna.configuration.network.area.AreaMasterService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/network/areas")
public class AreaController {

    @Autowired
    private AreaMasterService service;

    @Autowired
    private UserService userService;


    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<AreaMaster>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<AreaMaster> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<AreaMaster> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) AreaMaster obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<AreaMaster> save(@RequestBody(required = true) AreaMaster obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }

}
