package af.gov.anar.ebreshna.helpdesk.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ModuleType {

    METERING ("METERING"),
    BILLING("BILLING"),
    COLLECTIONS("COLLECTIONS"),
    SERVICE_CONNECTION("SERVICE_CONNECTION"),
    CUSTOMER_SUPPORT("CUSTOMER_SUPPORT"),
    INVENTORY_MANAGEMENT("INVENTORY_MANAGEMENT"),
    ASSET_MANAGEMENT("ASSET_MANAGEMENT"),
    RPS("RPS"),
    HELP_DESK("HELP_DESK"),
    SPOT_BILLING("SPOT_BILLING");

    private String content;

}
