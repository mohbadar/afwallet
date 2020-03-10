package af.gov.anar.ebreshna.configuration.billing.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SeasonType {

    NORMAL("Normal"),
    SEASONABLE("Seasonal");

    private String content;
}
