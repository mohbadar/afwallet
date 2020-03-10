package af.gov.anar.spm.infrastructure.dto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * This class is to capture the time duration for each event
 */
@Getter
@Setter
public class AuditDTO extends BaseDTO {
    protected String uuid;
    protected LocalDateTime createdAt;
    protected String eventId;
    protected String eventName;
    protected String eventType;
    protected LocalDateTime actionTimeStamp;
    protected String hostName;
    protected String hostIp;
    protected String applicationId;
    protected String applicationName;
    protected String sessionUserId;
    protected String sessionUserName;
    protected String id;
    protected String idType;
    protected String createdBy;
    protected String moduleName;
    protected String moduleId;
    protected String description;

}

