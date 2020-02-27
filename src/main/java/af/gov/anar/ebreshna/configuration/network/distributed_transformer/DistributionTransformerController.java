package af.gov.anar.ebreshna.configuration.network.distributed_transformer;

import af.gov.anar.ebreshna.configuration.network.distributed_transformer.DistributionTransformerMaster;
import af.gov.anar.ebreshna.configuration.network.distributed_transformer.DistributionTransformerMasterService;
import af.gov.anar.ebreshna.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/config/network/transformers")
public class DistributionTransformerController {

    @Autowired
    private DistributionTransformerMasterService service;


    @Autowired
    private UserService userService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<DistributionTransformerMaster>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<DistributionTransformerMaster> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<DistributionTransformerMaster> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) DistributionTransformerMaster obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<DistributionTransformerMaster> save(@RequestBody(required = true) DistributionTransformerMaster obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}
