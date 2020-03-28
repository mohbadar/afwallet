package af.gov.anar.corona.infrastructure.enumeration;

import lombok.Getter;

import static af.gov.anar.lib.audit.util.AuditEventType.USER_EVENT;
import static af.gov.anar.lib.audit.util.AuditEventType.SYSTEM_EVENT;

/**
 * Enum for Audit Events
 */
public enum AuditEvent {

    // Login
    CREATE_ORGANIZATION("SERVICENAME-EVT-001", USER_EVENT.getCode(), "CREATE_ORGANIZATION", "Create New Organization in System: Click of Submit"),
    UPDATE_ORGANIZATION("SERVICENAME-EVT-002", USER_EVENT.getCode(), "LOGIN_WITH_PASSWORD", "Update Details of Organization: Click of Submit"),
    DELETE_ORGANIZATION("SERVICENAME-EVT-003", USER_EVENT.getCode(), "LOGIN_GET_OTP", "Delete an organization from system: Click of Delete"),
    UPDATE_ORGANIZATION_CODE("SERVICENAME-EVT-004", USER_EVENT.getCode(), "LOGIN_SUBMIT_OTP", "Update Organization Code: Submit New Code of Organization");

    //Other events comes here

    /**
     * The constructor
     */
    private AuditEvent(String id, String type, String name, String description) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
    }

    @Getter
    private final String id;
    @Getter
    private final String type;
    @Getter
    private final String name;
    @Getter
    private final String description;
    

}
