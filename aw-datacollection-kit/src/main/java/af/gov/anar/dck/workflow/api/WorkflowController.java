
package af.gov.anar.dck.workflow.api;

import af.gov.anar.dck.form.model.Form;
import af.gov.anar.dck.form.service.FormService;
import af.gov.anar.dck.infrastructure.logger.Loggable;
import af.gov.anar.dck.instance.model.Instance;
import af.gov.anar.dck.instance.model.InstanceTransition;
import af.gov.anar.dck.instance.service.InstanceService;
import af.gov.anar.dck.useradministration.model.User;
import af.gov.anar.dck.useradministration.service.UserService;
import af.gov.anar.dck.workflow.model.Workflow;
import af.gov.anar.dck.workflow.service.WorkflowService;
import com.google.gson.Gson;
import af.gov.anar.dck.common.auth.UserAuthService;
import af.gov.anar.dck.instance.service.InstanceTransitionServiceImpl;
import af.gov.anar.dck.infrastructure.util.ExceptionUtil;
import af.gov.anar.dck.infrastructure.util.JsonParserUtil;
import af.gov.anar.dck.workflow.util.WorkflowJsonUtil;
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
@RequestMapping(value = { "/api/workflows" })
@Slf4j
public class WorkflowController {

    @Value("${spring.mail.to}")
    private String emailTo;

    @Autowired
    private WorkflowService workflowService;
    @Autowired
    private FormService formService;
    @Autowired
    private InstanceService instanceService;
    private WorkflowJsonUtil workflowJsonUtil=  new WorkflowJsonUtil();

    @Autowired
    private InstanceTransitionServiceImpl instanceTransitionService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
	private ExceptionUtil exceptionUtil;

    private String failureEmailSub = "500 - ASIMS Exception - Workflow";

//    Testing
//    @PostConstruct
//    public void init()
//    {
//        Instance instance = instanceService.findById(1L);
//
//        workflowJsonUtil.parse(instance.getForm().getWorkflow().getWorkflowJson());
//        System.out.println(workflowJsonUtil.getCurrentStep(instance.getCurrentStep(), instance.getForm().getWorkflow().getWorkflowJson()));
//    }

    @Loggable
    @GetMapping(value = { "" }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<Workflow> findAll() {
        String envSlug = userService.getCurrentEnv();
        return workflowService.findAllByEnv(envSlug);
    }

    @Loggable
    @GetMapping(value = { "/{id}" }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public Workflow findById(@PathVariable("id") Long id) {
        return workflowService.findById(id);
    }

    @Loggable
    @PostMapping(value = { "" }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public Workflow save(@Valid @RequestBody String data) {
        Gson g = new Gson();
        Workflow workflow = g.fromJson(data, Workflow.class);
        return workflowService.createOrUpdate(workflow);
    }

    @Loggable
    @PutMapping(value = { "/{id}" }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public Workflow update(@Valid @PathVariable("id") Long id, @RequestBody String data) {
        Gson g = new Gson();
        Workflow workflow = g.fromJson(data, Workflow.class);
        return workflowService.update(workflow, id);
    }

    @Loggable
    @DeleteMapping(value = { "/{id}" }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public Workflow delete(@PathVariable("id") Long id) {
        return workflowService.delete(id);
    }

    @Loggable
    @DeleteMapping(value = { "/delete" }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public HttpStatus delete(@RequestBody Workflow workflow) {
        Assert.isNull(workflow, "Workflow can not be null.");
        Assert.isNull(workflow.getName(), "Name of workflow is mandotery.");
        workflowService.delete(workflow);
        return HttpStatus.ACCEPTED;
    }

    @Loggable
    @GetMapping(
        value = {"/form/{id}"}, produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public Workflow findWorkflowByFormId(HttpServletRequest request, @PathVariable(value = "id", required = true) Long id) throws Exception
    {
        User currentLoggedInUser = userAuthService.getLoggedInUser();
        try {
            Form form = formService.findById(id);
            return form.getWorkflow();
        } catch (Exception e) {
            exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userAuthService.getCurrentEnv(), null, request, false);
            return null;
        }
    }

    @Loggable
    @GetMapping(
        value = {"/steps/{workflowId}"}, produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public String getAllSteps(@PathVariable(value = "workflowId", required = true) long id)
    {
        JSONObject jsonObject = workflowJsonUtil.parse(workflowService.findById(id).getWorkflowJson());
        return jsonObject.getJSONArray("steps").toString();
    }

    @Loggable
    @GetMapping(
        value = {"/next_steps/{instanceId}"},
        produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public String getCurrentStep(@PathVariable(value = "instanceId") long instanceId, HttpServletRequest request)
            throws Exception
    {
        User currentLoggedInUser = userAuthService.getLoggedInUser();
        try {
            Instance instance = instanceService.findById(instanceId);
            Workflow instanceWorkflow = instance.getForm().getWorkflow();
            String currentStep = null;
            if(instanceWorkflow != null) {
                workflowJsonUtil.parse(instanceWorkflow.getWorkflowJson());
                currentStep = workflowJsonUtil.getCurrentStep(instance.getCurrentStep(), instanceWorkflow.getWorkflowJson(), userService.getLoggedInUser().getGroups()).toString();
            }
            return currentStep;
        } catch (Exception e) {
            exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userAuthService.getCurrentEnv(), null, request, false);
            return null;
        }
    }


    @Loggable
    @PostMapping(
        value = "/move_next_step/{instanceId}"
    )
    public ResponseEntity<Void> moveToNextStep(HttpServletRequest request, @PathVariable(value = "instanceId", required = true) Long id, @RequestBody(required = true) String data)
            throws Exception
    {
        User currentLoggedInUser = userAuthService.getLoggedInUser();
        try {
            JsonParserUtil jsonParserUtil = new JsonParserUtil();
            JSONObject json = jsonParserUtil.parse(data);
            String nextStepName = json.getString("nextStep");
            String resolution = json.getString("resolution");
            String comment = json.getString("comment");

            User user = userService.getLoggedInUser();
            Instance instance = instanceService.findById(id);

            InstanceTransition transition = new InstanceTransition();
            transition.setUser(user);
            transition.setInstance(instance);
            transition.setNextStep(nextStepName);
            transition.setPreviousStep(instance.getCurrentStep());

            if(resolution != null || !resolution.trim().equals(""))
            {
                transition.setResolution(resolution);
                instance.setResolution(resolution);
            }

            if(!comment.trim().equals("") || comment != null)
            {
                transition.setRemarks(comment);
            }

            //Save as history of instance transitions
            instanceTransitionService.saveOrUpdate(transition);

            //set next step for instance
            instance.setCurrentStep(nextStepName);

            instanceService.create(instance);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userAuthService.getCurrentEnv(), null, request, false);
            return null;
        }
    }

}
