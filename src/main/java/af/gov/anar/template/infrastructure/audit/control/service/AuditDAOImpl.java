package af.gov.anar.template.infrastructure.audit.control.service;


import java.time.LocalDateTime;
import java.util.List;

import af.gov.anar.lib.audit.data.Audit;
import af.gov.anar.lib.logger.Logger;
import af.gov.anar.template.infrastructure.audit.control.repository.RegAuditRepository;
import af.gov.anar.template.infrastructure.constant.ApplicationGenericConstants;
import af.gov.anar.template.infrastructure.constant.ExceptionConstants;
import af.gov.anar.template.infrastructure.exception.RegBaseUncheckedException;
import af.gov.anar.template.infrastructure.util.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



/**
 * The implementation class of {@link AuditDAO}
 */
@Repository
public class AuditDAOImpl implements AuditDAO {

    @Autowired
    private RegAuditRepository regAuditRepository;

    /** Object for Logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AuditDAOImpl.class);

    @Override
    @Transactional
    public void deleteAll(LocalDateTime auditLogFromDtimes, LocalDateTime auditLogToDtimes) {
        LOGGER.info(ApplicationGenericConstants.LOG_AUDIT_DAO, ApplicationGenericConstants.APPLICATION_NAME,
                ApplicationGenericConstants.APPLICATION_ID, "Deleting Audit Logs");
        regAuditRepository.deleteAllInBatchBycreatedAtBetween(auditLogFromDtimes, auditLogToDtimes);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * io.mosip.registration.dao.AuditDAO#getAudits(io.mosip.registration.entity.
     * RegistrationAuditDates)
     */
    @Override
    public List<Audit> getAudits(RegistrationAuditDates registrationAuditDates) {
        LOGGER.info("REGISTRATION - FETCH_UNSYNCED_AUDITS - GET_ALL_AUDITS", ApplicationGenericConstants.APPLICATION_NAME, ApplicationGenericConstants.APPLICATION_ID,
                "Fetching of unsynchronized which are to be added to Registartion packet started");

        try {
            List<Audit> audits;
            if (registrationAuditDates == null || registrationAuditDates.getAuditLogToDateTime() == null) {
                audits = regAuditRepository.findAllByOrderByCreatedAtAsc();
            } else {
                audits = regAuditRepository.findByCreatedAtGreaterThanOrderByCreatedAtAsc(
                        registrationAuditDates.getAuditLogToDateTime().toLocalDateTime());
            }

            LOGGER.info("REGISTRATION - FETCH_UNSYNCED_AUDITS - GET_ALL_AUDITS", ApplicationGenericConstants.APPLICATION_NAME, ApplicationGenericConstants.APPLICATION_ID,
                    "Fetching of unsynchronized which are to be added to Registartion packet ended");

            return audits;
        } catch (RuntimeException exception) {
            throw new RegBaseUncheckedException(ExceptionConstants.REG_GET_AUDITS_EXCEPTION.getErrorCode(),
                    ExceptionConstants.REG_GET_AUDITS_EXCEPTION.getErrorMessage(), exception);
        }
    }

}
