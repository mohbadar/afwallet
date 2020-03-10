package af.gov.anar.ebreshna.configuration.billing.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TariffModifyCategory {

    CATEGORY("Category"),
    SUB_CATEGORY("Sub Gategory");

    private String content;
}
