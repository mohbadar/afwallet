package af.gov.anar.dck.form.api;

import af.gov.anar.dck.form.model.FormSyncEndpointsQueue;
import af.gov.anar.dck.form.service.FormService;
import af.gov.anar.dck.useradministration.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import af.gov.anar.dck.common.auth.UserAuthService;
import af.gov.anar.dck.form.service.FormSyncEndpointsQueueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/form-sync-endpoints")
public class FormSyncEndpointsQueueController{

    @Autowired
    private FormSyncEndpointsQueueServiceImpl formSyncEndpointsQueueService;

    @Autowired
    private FormService formService;

    @Autowired
    private UserService userService;


    @Autowired
    private UserAuthService userAuthService;


    ObjectMapper mapper = new ObjectMapper();

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<FormSyncEndpointsQueue>> findAll() {
        return ResponseEntity.ok(formSyncEndpointsQueueService.findAll());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<FormSyncEndpointsQueue> findById(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(formSyncEndpointsQueueService.findById(id));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<FormSyncEndpointsQueue> store(@Valid @RequestBody String formSyncEndpointsQueue)
            throws IOException
    {

        JsonNode root = mapper.readTree(formSyncEndpointsQueue);
        String name = root.get("name").asText();
        long formId = root.get("form").asLong();
        String endpointUrl = root.get("endpointUrl").asText();

        FormSyncEndpointsQueue queue = new FormSyncEndpointsQueue();
        queue.setEndpointUrl(endpointUrl);
        queue.setEnvSlug(userAuthService.getCurrentEnv());
        queue.setForm(formService.findById(formId));
        queue.setName(name);

        System.out.println("FormData = "+ queue.toString());

        return ResponseEntity.ok(formSyncEndpointsQueueService.store(queue));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Map<String, Object>> delete(@PathVariable(value = "id", required = true) Long id)
    {
        Map<String, Object> data = new HashMap<>();
        data.put("status", HttpStatus.ACCEPTED);

        formSyncEndpointsQueueService.deleteById(id);

        return ResponseEntity.ok(data);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<FormSyncEndpointsQueue> update(@Valid @RequestBody String formSyncEndpointsQueue, @PathVariable(value = "id", required = true) Long id)
            throws IOException
    {
        JsonNode root = mapper.readTree(formSyncEndpointsQueue);
        String name = root.get("name").asText();
        String endpointUrl = root.get("endpointUrl").asText();

        FormSyncEndpointsQueue queue = new FormSyncEndpointsQueue();
        queue.setEndpointUrl(endpointUrl);
        queue.setName(name);
        queue.setId(id);

        return ResponseEntity.ok(formSyncEndpointsQueueService.store(queue));
    }
}