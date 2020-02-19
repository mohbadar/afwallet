package af.gov.anar.ebreshna.common.dlist.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DListType {

    DEFAULT("Default"),
    CYCLE_BASED("Cycle Based");

    private String content;
}
