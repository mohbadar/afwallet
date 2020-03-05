package af.gov.anar.ebreshna.helpdesk.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ParentProcess {
    PENDING ("PENDING"),
    RECTIFIED ("RECTIFIED"),
    INVALID ("INVALID"),
    ESCLATION("ESCLATION");

    private String content;
}
