package af.gov.anar.ebreshna.nsc.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PropertyType {

    RESIDENDCE("Residence"),
    FACTORY("Factory");

    private String content;

}
