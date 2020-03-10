package af.gov.anar.lib.workflow;

import af.gov.anar.lang.infrastructure.exception.common.IOException;
import af.gov.anar.lib.json.JsonUtility;
import af.gov.anar.lib.json.exception.JsonMappingException;
import af.gov.anar.lib.json.exception.JsonParseException;
import af.gov.anar.lib.workflow.model.Workflow;
import af.gov.anar.lib.workflow.parser.WorkflowParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AnarLibWorkflowApplication {


	public static void main(String[] args) throws JsonParseException, IOException, JsonMappingException {
		SpringApplication.run(AnarLibWorkflowApplication.class, args);
	}

}
