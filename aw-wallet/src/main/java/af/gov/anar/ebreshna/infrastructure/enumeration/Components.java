package af.gov.anar.ebreshna.infrastructure.enumeration;

import lombok.Getter;

/**
 * Enum for Application Modules to be used in Audit
 */
public enum Components {

    ORGANIZATION("SERVICENAME-ANAR-101", "ORGANIZATION"),
    PROJECT("SERVICENAME-ANAR-102", "PROJECT"),
    PROGRAME("SERVICENAME-ANAR-103", "PROGRAME"),
    FUND("SERVICENAME-ANAR-104", "FUND"),
    OFFICE("SERVICENAME-ANAR-105", "OFFICE");

    /**
     * The constructor
     */
    private Components(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Getter
    private final String id;
    @Getter
    private final String name;

}