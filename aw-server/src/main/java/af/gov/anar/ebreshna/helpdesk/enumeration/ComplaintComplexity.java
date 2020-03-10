package af.gov.anar.ebreshna.helpdesk.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum  ComplaintComplexity {


    CRITICAL ("CRITICAL"),
    HIGH ("HIGH"),
    MEDIUM ("MEDIUM"),
    LOW("LOW");

    private String content;
}
