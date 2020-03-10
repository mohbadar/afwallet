package af.gov.anar.lib.anarlibworkflow;


import af.gov.anar.lib.workflow.AnarLibWorkflowApplication;
import af.gov.anar.lib.workflow.model.Workflow;
import af.gov.anar.lib.workflow.parser.WorkflowParser;
import af.gov.anar.lib.workflow.repository.WorkflowRepository;
import af.gov.anar.lib.workflow.service.WorkflowService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = AnarLibWorkflowApplication.class)
public class WorkflowTest {


    @Autowired
    private WorkflowService workflowService;

    @MockBean
    private WorkflowRepository workflowRepository;

    WorkflowParser workflowParser = new WorkflowParser();;

    private static final String EXPECTED_WORKFLOW_STEPS = "[{\"requiredAuthorities\":[\"ADMIN_ROLE\"],\"name\":\"Open\",\"transitions\":[{\"toStep\":\"Rejected\",\"CommentRequired\":true,\"name\":\"Reject\"},{\"toStep\":\"Closed\",\"CommentRequired\":true,\"name\":\"Close\",\"resolutions\":[\"Completed\",\"Incomplete\",\"Duplicate\"]}]},{\"requiredAuthorities\":[\"ADMIN_ROLE\"],\"name\":\"Reopened\",\"transitions\":[{\"toStep\":\"Rejected\",\"CommentRequired\":true,\"name\":\"Reject\"},{\"toStep\":\"Closed\",\"CommentRequired\":true,\"name\":\"Close\",\"resolutions\":[\"Completed\",\"Incomplete\",\"Duplicate\"]}]},{\"requiredAuthorities\":[\"ADMIN_ROLE\"],\"name\":\"Rejected\",\"transitions\":[{\"toStep\":\"Reopened\",\"CommentRequired\":false,\"name\":\"Reopen\"},{\"toStep\":\"Closed\",\"CommentRequired\":true,\"name\":\"Close\",\"resolutions\":[\"Completed\",\"Incomplete\",\"Duplicate\"]}]},{\"requiredAuthorities\":[\"ADMIN_ROLE\"],\"name\":\"Closed\",\"transitions\":[]}]\n";
    private static final String WORKFLOW_STRING = "{\n" +
            "   \"steps\":[\n" +
            "        {\"name\":\"Open\", \"transitions\":[\n" +
            "                {\"name\":\"Reject\", \"toStep\":\"Rejected\", \"CommentRequired\": true},\n" +
            "                {\"name\":\"Close\",\"toStep\":\"Closed\",\"resolutions\":[\"Completed\", \"Incomplete\", \"Duplicate\"], \"CommentRequired\": true}\n" +
            "        ],\n" +
            "        \"requiredAuthorities\": [\"ADMIN_ROLE\"],\n" +
            "        },\n" +
            "        {\"name\":\"Reopened\", \"transitions\":[\n" +
            "                {\"name\":\"Reject\", \"toStep\":\"Rejected\", \"CommentRequired\": true},\n" +
            "                {\"name\":\"Close\", \"toStep\":\"Closed\", \"resolutions\":[\"Completed\", \"Incomplete\", \"Duplicate\"], \"CommentRequired\": true}\n" +
            "        ],\n" +
            "        \"requiredAuthorities\": [ \"ADMIN_ROLE\"],\n" +
            "        },\n" +
            "        {\"name\":\"Rejected\", \"transitions\":[\n" +
            "                {\"name\":\"Reopen\", \"toStep\":\"Reopened\", \"CommentRequired\": false},\n" +
            "                {\"name\":\"Close\", \"toStep\":\"Closed\", \"resolutions\":[\"Completed\", \"Incomplete\", \"Duplicate\"], \"CommentRequired\": true}\n" +
            "        ],\n" +
            "        \"requiredAuthorities\": [\"ADMIN_ROLE\"],\n" +
            "        },\n" +
            "        {\"name\":\"Closed\", \"transitions\":[ ],\n" +
            "        \"requiredAuthorities\": [\"ADMIN_ROLE\"],\n" +
            "        }\n" +
            "    ]\n" +
            "}";
//    @Test
    public void getAllStepsTest()
    {
        Workflow workflow = new Workflow();
        workflow.setName("workflow default");
        workflow.setWorkflowJson(WORKFLOW_STRING);

    }

    @Test
    public void workflowStoreTest() {

        Mockito.when(workflowRepository.save(ArgumentMatchers.any(Workflow.class))).thenReturn(new Workflow());


        Workflow corsEntity = Workflow.builder()
                .name("Default Workflow")
                .description("Default Workflow Description")
                .workflowJson(WORKFLOW_STRING)
                .build();

        Workflow result = workflowService.createOrUpdate(corsEntity);

        assertTrue(result != null);
    }


}
