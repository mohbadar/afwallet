package af.gov.anar.template.infrastructure.audit.control.service;


import java.sql.Timestamp;
import java.util.List;

import af.gov.anar.template.infrastructure.audit.control.entity.AuditLogControl;
/**
 * This class is used to control all the operations on the {@link AuditLogControl}.
 */
public interface AuditLogControlDAO {

    /**
     * This method is used to find the audit log start and end {@link Timestamp} of the latest
     * registration from {@link AuditLogControl}
     *
     * @return {@link RegistrationAuditDates}
     */
    RegistrationAuditDates getLatestRegistrationAuditDates();

    /**
     * This method is used to save the {@link AuditLogControl} for the latest {@link } packet
     *
     * @param auditLogControl
     *            the {@link AuditLogControl} object to be saved
     */
    void save(AuditLogControl auditLogControl);

    /**
     * This method is used to delete the audit log control
     *
     * @param auditLogControl
     *            the {@link AuditLogControl} object to be deleted
     */
    void delete(AuditLogControl auditLogControl);

    /**
     * This method is used to fetch the Audit Log Control
     *
     * @param req
     *            request time upto which logs need to be retrieved
     *
     * @return list of Audit logs
     */
    List<AuditLogControl> get(Timestamp req);

    /**
     * This method is used to get Audit Log Control using registration Id
     *
     */
    AuditLogControl get(String regId);

}
