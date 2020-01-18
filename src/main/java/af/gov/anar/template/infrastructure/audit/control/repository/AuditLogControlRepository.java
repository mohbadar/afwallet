package af.gov.anar.template.infrastructure.audit.control.repository;


import af.gov.anar.template.infrastructure.audit.control.entity.AuditLogControl;
import af.gov.anar.template.infrastructure.audit.control.service.RegistrationAuditDates;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;


/**
 * Repository interface for {@link AuditLogControl} table

 */
public interface AuditLogControlRepository extends JpaRepository<AuditLogControl, String> {

    /**
     * Fetch the AuditLogFromDateTime and AuditLogToDateTime of the latest
     * {@link AuditLogControl}
     *
     * @return the {@link RegistrationAuditDates}
     */
    RegistrationAuditDates findTopByOrderByCrDtimeDesc();

    /**
     * Fetch the Audit Logs upto req time
     *
     * @param req
     *            upto Audit logs to be retrieved
     * @return list of audit log controls
     */
    List<AuditLogControl> findByCrDtimeBefore(Timestamp req);

}
