package af.gov.anar.ebreshna.common.workflow;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum  WorkflowType {

    HELP_DESK_WORKFLOW("HELP_DESK_WORKFLOW");

    private String content;
}
