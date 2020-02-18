package af.gov.anar.ebreshna.common.office.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  PremisesCategory {

    RESIDENTIAL ("Residential"),
    GOVERNMENT ("Government"),
    HOLY_PLACE ("Holy Place"),
    COMMERCIAL ("Commercial"),
    REGISTERED_INDUSTRIES ("Registered Insdustries");

    private String content;
}
