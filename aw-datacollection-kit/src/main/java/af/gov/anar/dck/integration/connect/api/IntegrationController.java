package af.gov.anar.dck.integration.connect.api;

import af.gov.anar.dck.integration.connect.service.IntegrationService;
import af.gov.anar.dck.infrastructure.util.JsonParserUtil;
import org.json.JSONObject;
import org.sourcelab.kafka.connect.apiclient.request.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/integrations")
public class IntegrationController {

    @Autowired
    private IntegrationService integrationService;

    private JsonParserUtil jsonParserUtil = new JsonParserUtil();

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Collection<String>> getConnectors()
    {
        return ResponseEntity.ok(integrationService.getConnectors());
    }

    @GetMapping(value = "/source", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Collection<String>> getSourceConnectors()
    {
        return ResponseEntity.ok(integrationService.getSourceConnectors());
    }


    @GetMapping(value = "/sink", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Collection<String>> getSinkConnectors()
    {
        return ResponseEntity.ok(integrationService.getSinkConnectors());
    }


    @GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<ConnectorDefinition> getConnector(@PathVariable(name = "name", required = true) String name)
    {
        ConnectorDefinition connectorDefinition = integrationService.getConnector(name);

        // Map<String, Object> data = new HashMap<>();
        // data.put("name", connectorDefinition.getName());
        // data.put("config", connectorDefinition.getConfig());
        // data.put("type", connectorDefinition.getType();
        // data.
        return ResponseEntity.ok(connectorDefinition);
    }

    
    @GetMapping(value = "/config/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Map<String, String>> getConnectorConfig(@PathVariable(name = "name", required = true) String name)
    {
        return ResponseEntity.ok(integrationService.getConnectorConfig(name));
    }

    @GetMapping(value = "/status/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<ConnectorStatus> getConnectorStatus(@PathVariable(name = "name", required = true) String name)
    {
        return ResponseEntity.ok(integrationService.getConnectorStatus(name));
    }


    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> addConnector(@RequestBody(required = true) String connectorString)
    {
        JSONObject jsonConfig = jsonParserUtil.parse(connectorString);
        
        String connectorName = jsonConfig.getString("name");
        String connectorConfig =jsonConfig.getString("config");

        System.out.println("Connector Config: "+ connectorConfig);
        Map<String, Object> data = new HashMap<>();
        ConnectorDefinition connectorDefinition = integrationService.deployConnector(connectorName, connectorConfig);
        if(connectorDefinition == null)
        {
            data.put("code", HttpStatus.METHOD_FAILURE);
            data.put("message", "Connector creation is fialed. Please contact your system admin");
            return ResponseEntity.ok(data);

        }else{
            integrationService.restartConnector(connectorName);
            return ResponseEntity.ok(connectorDefinition);
        }
    }

    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> updateConnector(@RequestBody(required = true) String connectorString) throws IOException
    {
        JSONObject jsonConfig = jsonParserUtil.parse(connectorString);
        
        String connectorName = jsonConfig.getString("name");
        String connectorConfig =jsonConfig.getString("config");

        Map<String, Object> data = new HashMap<>();
        ConnectorDefinition connectorDefinition = integrationService.updateConnectorConfig(connectorName, jsonParserUtil.convertJsonStringToMap(connectorConfig));
        if(connectorDefinition == null)
        {
            data.put("code", HttpStatus.METHOD_FAILURE);
            data.put("message", "Connector update is fialed. Please contact your system admin");
            return ResponseEntity.ok(data);

        }else{
            integrationService.restartConnector(connectorName); 
            return ResponseEntity.ok(connectorDefinition);
        }
    }

    
    @GetMapping(value = "/resume/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> resumeConnector(@PathVariable( value = "name", required = true) String name)
    {
        Map<String, Object> data = new HashMap<>();
        Boolean result = integrationService.resumeConnector(name);
        if(!result)
        {
            data.put("code", HttpStatus.METHOD_FAILURE);
            data.put("message", "Connector resume process is fialed. Please contact your system admin");
            return ResponseEntity.ok(data);

        }else{
            data.put("code", HttpStatus.OK);
            data.put("message", "Connector resume process was successful.");
            return ResponseEntity.ok(data);
        }
    }


        
    @GetMapping(value = "/restart/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> restartConnector(@PathVariable( value = "name", required = true) String name)
    {
        Map<String, Object> data = new HashMap<>();
        Boolean result = integrationService.restartConnector(name);
        if(!result)
        {
            data.put("code", HttpStatus.METHOD_FAILURE);
            data.put("message", "Connector restart process is fialed. Please contact your system admin");
            return ResponseEntity.ok(data);

        }else{
            data.put("code", HttpStatus.OK);
            data.put("message", "Connector restart process was successful.");
            return ResponseEntity.ok(data);
        }
    }

        
    @DeleteMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> deleteConnector(@PathVariable( value = "name", required = true) String name)
    {
        Map<String, Object> data = new HashMap<>();
        Boolean result = integrationService.deleteConnector(name);
        if(!result)
        {
            data.put("code", HttpStatus.METHOD_FAILURE);
            data.put("message", "Connector delete process is fialed. Please contact your system admin");
            return ResponseEntity.ok(data);

        }else{
            data.put("code", HttpStatus.OK);
            data.put("message", "Connector delete process was successful.");
            return ResponseEntity.ok(data);
        }
    }


        
    @GetMapping(value = "/task/restart/{name}/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> restartConnectorTask(@PathVariable( value = "name", required = true) String name, @PathVariable( value = "taskId", required = true) int taskId)
    {
        Map<String, Object> data = new HashMap<>();
        Boolean result = integrationService.restartConnectorTask(name, taskId);
        if(!result)
        {
            data.put("code", HttpStatus.METHOD_FAILURE);
            data.put("message", "Connector task restart process is fialed. Please contact your system admin");
            return ResponseEntity.ok(data);

        }else{
            data.put("code", HttpStatus.OK);
            data.put("message", "Connector task restart process was successful.");
            return ResponseEntity.ok(data);
        }
    }


    @GetMapping(value = "/plugins", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> getConnectorPlugins()
    {
        Map<String, Object> data = new HashMap<>();
        Collection<ConnectorPlugin> connectorPlugins = integrationService.getConnectorPlugins();
        if(connectorPlugins == null)
        {
            data.put("code", HttpStatus.NOT_FOUND);
            data.put("message", "No Plugin is available");
            return ResponseEntity.ok(data);

        }else{
            data.put("code", HttpStatus.OK);
            data.put("plugins", connectorPlugins);
            return ResponseEntity.ok(data);
        }
    }


    @PostMapping(value = "/plugins/varify-config/{pluginName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> validateConnectorPluginConfig(@PathVariable(required = true, name="pluginName") String name, @RequestParam(required = true) String config) throws IOException
    {
        ConnectorPluginConfigDefinition definition = new ConnectorPluginConfigDefinition(name, jsonParserUtil.convertJsonStringToMap(config));
        ConnectorPluginConfigValidationResults validateConnectorPluginConfig = integrationService.validateConnectorPluginConfig(definition);
        return ResponseEntity.ok(validateConnectorPluginConfig);
        
    }


}