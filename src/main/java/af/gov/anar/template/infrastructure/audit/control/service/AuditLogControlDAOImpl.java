package af.gov.anar.template.infrastructure.audit.control.service;

import java.sql.Timestamp;
import java.util.List;

import af.gov.anar.lib.logger.Logger;
import af.gov.anar.template.infrastructure.audit.control.entity.AuditLogControl;
import af.gov.anar.template.infrastructure.audit.control.repository.AuditLogControlRepository;
import af.gov.anar.template.infrastructure.constant.ApplicationGenericConstants;
import af.gov.anar.template.infrastructure.util.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * DAO class for the {@link AuditLogControl} entity
 */
@Repository
public class AuditLogControlDAOImpl  extends BaseService implements AuditLogControlDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditLogControlDAOImpl.class);
    @Autowired
    private AuditLogControlRepository auditLogControlRepository;

    @Autowired
    private AuditDAO auditDAO;

    /*
     * (non-Javadoc)
     *
     * @see
     * io.mosip.registration.dao.AuditLogControlDAO#getLatestRegistrationAuditDates(
     * )
     */
    @Override
    public RegistrationAuditDates getLatestRegistrationAuditDates() {
        LOGGER.info("AUDIT - GET_LATEST_REGISTRATION_AUDIT_DATES - AUDIT_LOG_CONTROL_DAO", ApplicationGenericConstants.APPLICATION_NAME,
                ApplicationGenericConstants.APPLICATION_ID, "Retrieving the latest audit logs start and end timestamps stored");

        return auditLogControlRepository.findTopByOrderByCrDtimeDesc();
    }

    /*
     * (non-Javadoc)
     *
     * @see io.mosip.registration.dao.AuditLogControlDAO#save(io.mosip.registration.
     * entity.AuditLogControl)
     */
    @Override
    public void save(AuditLogControl auditLogControl) {
        LOGGER.info("AUDIT - SAVE_AUDIT_LOG_CONTROL - AUDIT_LOG_CONTROL_DAO", ApplicationGenericConstants.APPLICATION_NAME,
                ApplicationGenericConstants.APPLICATION_ID, "Saves the audit log control for the latest registration packet");

        auditLogControlRepository.save(auditLogControl);
    }

    /* (non-Javadoc)
     * @see io.mosip.registration.dao.AuditLogControlDAO#delete(io.mosip.registration.entity.AuditLogControl)
     */
    @Override
    public void delete(AuditLogControl auditLogControl) {

        LOGGER.debug("AUDIT - DELETE_AUDIT_LOG_CONTROL - AUDIT_LOG_CONTROL_DAO", ApplicationGenericConstants.APPLICATION_NAME, ApplicationGenericConstants.APPLICATION_ID,
                "Started Deleting the audit log control for the registration packet");

        /* Delete Audit Logs */
        auditDAO.deleteAll(auditLogControl.getAuditLogFromDateTime().toLocalDateTime(),
                auditLogControl.getAuditLogToDateTime().toLocalDateTime());

        /* Delete Audit Control Log */
        auditLogControlRepository.delete(auditLogControl);

        LOGGER.debug("AUDIT - DELETE_AUDIT_LOG_CONTROL - AUDIT_LOG_CONTROL_DAO", ApplicationGenericConstants.APPLICATION_NAME, ApplicationGenericConstants.APPLICATION_ID,
                "Started Deleting the audit log control for the registration packet");

    }

    @Override
    public List<AuditLogControl> get(Timestamp req) {
        LOGGER.debug("AUDIT - GET_AUDIT_LOG_CONTROL - AUDIT_LOG_CONTROL_DAO", ApplicationGenericConstants.APPLICATION_NAME, ApplicationGenericConstants.APPLICATION_ID,
                "Started fetching List of audit log control  before req Time");


        return auditLogControlRepository.findByCrDtimeBefore(req);
    }

    @Override
    public AuditLogControl get(String regId) {
        LOGGER.debug("AUDIT - GET_AUDIT_LOG_CONTROL - AUDIT_LOG_CONTROL_DAO", ApplicationGenericConstants.APPLICATION_NAME, ApplicationGenericConstants.APPLICATION_ID,
                "Started fetching audit log control  using registartion Id");

        return  auditLogControlRepository.findById(regId).get();
    }

}
