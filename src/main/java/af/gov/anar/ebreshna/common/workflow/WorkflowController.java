
package af.gov.anar.ebreshna.common.workflow;

import af.gov.anar.ebreshna.infrastructure.service.UserService;
import af.gov.anar.lib.workflow.model.Workflow;
import af.gov.anar.lib.workflow.parser.WorkflowParser;
import af.gov.anar.lib.workflow.service.WorkflowService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = { "/api/workflows", })
@Slf4j
public class WorkflowController {


    @Autowired
    private WorkflowService workflowService;

    private WorkflowParser workflowJsonUtil=  new WorkflowParser();

    @Autowired
    private UserService userService;

    @GetMapping(value = { "" }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<Workflow> findAll() {
        return workflowService.findAll();
    }
    
    @GetMapping(value = { "/{id}" }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public Workflow findById(@PathVariable("id") Long id) {
        return workflowService.findById(id);
    }
    
    @PostMapping(value = { "" }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public Workflow save(@Valid @RequestBody String data) {
        Gson g = new Gson();
        Workflow workflow = g.fromJson(data, Workflow.class);
        return workflowService.createOrUpdate(workflow);
    }

    @PutMapping(value = { "/{id}" }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public Workflow update(@Valid @PathVariable("id") Long id, @RequestBody String data) {
        Gson g = new Gson();
        Workflow workflow = g.fromJson(data, Workflow.class);
        return workflowService.update(workflow, id);
    }


    @DeleteMapping(value = { "/{id}" }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public Workflow delete(@PathVariable("id") Long id) {
        return workflowService.delete(id);
    }

    @DeleteMapping(value = { "/delete" }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public HttpStatus delete(@RequestBody Workflow workflow) {
        Assert.isNull(workflow, "Workflow can not be null.");
        Assert.isNull(workflow.getName(), "Name of workflow is mandotery.");
        workflowService.delete(workflow);
        return HttpStatus.ACCEPTED;
    }



    @GetMapping(
        value = {"/steps/{workflowId}"}, produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public String getAllSteps(@PathVariable(value = "workflowId", required = true) long id)
    {
        JSONObject jsonObject = workflowJsonUtil.parse(workflowService.findById(id).getWorkflowJson());
        return jsonObject.getJSONArray("steps").toString();
    }

//    @GetMapping(
//        value = {"/next_steps/{instanceId}"},
//        produces = {MediaType.APPLICATION_JSON_VALUE}
//    )
//    public String getCurrentStep(@PathVariable(value = "instanceId") long instanceId, HttpServletRequest request)
//            throws Exception
//    {
//        try {
//            Instance instance = instanceService.findById(instanceId);
//            Workflow instanceWorkflow = instance.getForm().getWorkflow();
//            String currentStep = null;
//            if(instanceWorkflow != null) {
//                workflowJsonUtil.parse(instanceWorkflow.getWorkflowJson());
//                currentStep = workflowJsonUtil.getCurrentStep(instance.getCurrentStep(), instanceWorkflow.getWorkflowJson(), userService.getLoggedInUser().getGroups()).toString();
//            }
//            return currentStep;
//        } catch (Exception e) {
//            return null;
//        }
//    }


    
//    @PostMapping(
//        value = "/move_next_step/{instanceId}"
//    )
//    public ResponseEntity<Void> moveToNextStep(HttpServletRequest request, @PathVariable(value = "instanceId", required = true) Long id, @RequestBody(required = true) String data)
//            throws Exception
//    {
//        User currentLoggedInUser = userAuthService.getLoggedInUser();
//        try {
//            JsonParserUtil jsonParserUtil = new JsonParserUtil();
//            JSONObject json = jsonParserUtil.parse(data);
//            String nextStepName = json.getString("nextStep");
//            String resolution = json.getString("resolution");
//            String comment = json.getString("comment");
//
//            User user = userService.getLoggedInUser();
//            Instance instance = instanceService.findById(id);
//
//            InstanceTransition transition = new InstanceTransition();
//            transition.setUser(user);
//            transition.setInstance(instance);
//            transition.setNextStep(nextStepName);
//            transition.setPreviousStep(instance.getCurrentStep());
//
//            if(resolution != null || !resolution.trim().equals(""))
//            {
//                transition.setResolution(resolution);
//                instance.setResolution(resolution);
//            }
//
//            if(!comment.trim().equals("") || comment != null)
//            {
//                transition.setRemarks(comment);
//            }
//
//            //Save as history of instance transitions
//            instanceTransitionService.saveOrUpdate(transition);
//
//            //set next step for instance
//            instance.setCurrentStep(nextStepName);
//
//            instanceService.create(instance);
//            return ResponseEntity.ok().build();
//
//        } catch (Exception e) {
//            exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userAuthService.getCurrentEnv(), null, request, false);
//            return null;
//        }
//    }

}
