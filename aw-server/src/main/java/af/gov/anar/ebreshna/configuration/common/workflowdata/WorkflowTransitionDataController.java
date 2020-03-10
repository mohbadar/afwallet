package af.gov.anar.ebreshna.configuration.common.workflowdata;

import af.gov.anar.api.handler.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/common/workflowtransitiondata")
public class WorkflowTransitionDataController extends ResponseHandler {

    @Autowired
    private WorkflowTransitionDataService service;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<List<WorkflowTransitionData>> findall()
    {
        return ResponseEntity.ok(service.findall());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<WorkflowTransitionData> findOne(@PathVariable(name = "id", required = true) long id)
    {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<WorkflowTransitionData> update(@PathVariable(name = "id", required = true) long id, @RequestBody(required = true) WorkflowTransitionData obj)
    {
        obj.setId(id);
        return ResponseEntity.ok(service.save(obj));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<WorkflowTransitionData> save(@RequestBody(required = true) WorkflowTransitionData obj)
    {
        return ResponseEntity.ok(service.save(obj));
    }
}
