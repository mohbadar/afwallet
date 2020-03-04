package af.gov.anar.ebreshna.helpdesk.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum  ComplaintStatus {

    PENDING("PENDING"),
    INFOREQ("INFOREQ"),
    RECTIFIED("RECTIFIED"),
    INVALID("INVALID"),
    APPROVED("APPROVED"),
    DUPLICATE("DUPLICATE");

    private String content;
}
