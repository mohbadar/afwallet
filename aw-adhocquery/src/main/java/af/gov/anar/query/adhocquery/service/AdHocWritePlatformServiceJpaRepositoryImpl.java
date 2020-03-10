
package af.gov.anar.query.adhocquery.service;

import java.util.Map;

import af.gov.anar.query.adhocquery.domain.AdHoc;
import af.gov.anar.query.adhocquery.domain.AdHocRepository;
import af.gov.anar.query.adhocquery.exception.AdHocNotFoundException;
import af.gov.anar.query.infrastructure.common.command.JsonCommand;
import af.gov.anar.lang.data.CommandProcessingResult;
import af.gov.anar.lang.data.CommandProcessingResultBuilder;
import af.gov.anar.lang.infrastructure.exception.common.PlatformDataIntegrityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdHocWritePlatformServiceJpaRepositoryImpl implements AdHocWritePlatformService {

    private final static Logger logger = LoggerFactory.getLogger(AdHocWritePlatformServiceJpaRepositoryImpl.class);
    private final AdHocRepository adHocRepository;
    private final AdHocDataValidator adHocCommandFromApiJsonDeserializer;
   

    @Autowired
    public AdHocWritePlatformServiceJpaRepositoryImpl( final AdHocRepository adHocRepository,
             final AdHocDataValidator adHocCommandFromApiJsonDeserializer) {
        this.adHocRepository = adHocRepository;
        this.adHocCommandFromApiJsonDeserializer = adHocCommandFromApiJsonDeserializer;
       
    }

    @Transactional
    @Override
    public CommandProcessingResult createAdHocQuery(final JsonCommand command) {

        try {

            this.adHocCommandFromApiJsonDeserializer.validateForCreate(command.json());

            final AdHoc entity = AdHoc.fromJson(command);
            this.adHocRepository.save(entity);

            return new CommandProcessingResultBuilder().withCommandId(command.commandId()).withEntityId(entity.getId()).build();
        } catch (final DataIntegrityViolationException dve) {
            handleDataIntegrityIssues(command, dve);
            return new CommandProcessingResultBuilder() //
                    .withCommandId(command.commandId()) //
                    .build();
        }
    }

    /*
     * Guaranteed to throw an exception no matter what the data integrity issue
     * is.
     */
    private void handleDataIntegrityIssues(final JsonCommand command, final DataIntegrityViolationException dve) {

        final Throwable realCause = dve.getMostSpecificCause();
        if (realCause.getMessage().contains("unq_name")) {

            final String name = command.stringValueOfParameterNamed("name");
            throw new PlatformDataIntegrityException("error.msg.adhocquery.duplicate.name", "AdHocQuery with name `" + name + "` already exists",
                    "name", name);
        }

        logAsErrorUnexpectedDataIntegrityException(dve);
        throw new PlatformDataIntegrityException("error.msg.adhocquery.unknown.data.integrity.issue",
                "Unknown data integrity issue with resource.");
    }

    private void logAsErrorUnexpectedDataIntegrityException(final DataIntegrityViolationException dve) {
        logger.error(dve.getMessage(), dve);
    }

    @Transactional
    @Override
    public CommandProcessingResult updateAdHocQuery(final Long adHocId, final JsonCommand command) {
        try {

            this.adHocCommandFromApiJsonDeserializer.validateForUpdate(command.json());

            final AdHoc adHoc = this.adHocRepository.getOne(adHocId);
            if (adHoc == null) { throw new AdHocNotFoundException(adHocId); }

            final Map<String, Object> changes = adHoc.update(command);
            if (!changes.isEmpty()) {
                this.adHocRepository.saveAndFlush(adHoc);
            }

            return new CommandProcessingResultBuilder() //
                    .withCommandId(command.commandId()) //
                    .withEntityId(adHocId) //
                    .with(changes) //
                    .build();
        } catch (final DataIntegrityViolationException dve) {
            handleDataIntegrityIssues(command, dve);
            return new CommandProcessingResultBuilder() //
                    .withCommandId(command.commandId()) //
                    .build();
        }
    }
    /**
     * Method for Delete adhoc
     */
    @Transactional
    @Override
    public CommandProcessingResult deleteAdHocQuery(Long adHocId) {

        try {
            /**
             * Checking the adhocQuery present in DB or not using adHocId
             */
            final AdHoc adHoc = this.adHocRepository.getOne(adHocId);
            if (adHoc == null) { throw new AdHocNotFoundException(adHocId); }
            
            this.adHocRepository.delete(adHoc);
            return new CommandProcessingResultBuilder().withEntityId(adHocId).build();
        } catch (final DataIntegrityViolationException e) {
            throw new PlatformDataIntegrityException("error.msg.unknown.data.integrity.issue",
                    "Unknown data integrity issue with resource: " + e.getMostSpecificCause());
        }
    }

    /**
     * Method for disabling the adhocquery
     */
    @Transactional
    @Override
    public CommandProcessingResult disableAdHocQuery(Long adHocId) {
        try {
            /**
             * Checking the adhocquery present in DB or not using adHocId
             */
            final AdHoc adHoc = this.adHocRepository.getOne(adHocId);
            if (adHoc == null) { throw new AdHocNotFoundException(adHocId); }
            adHoc.disableActive();
            this.adHocRepository.save(adHoc);
            return new CommandProcessingResultBuilder().withEntityId(adHocId).build();

        } catch (final DataIntegrityViolationException e) {
            throw new PlatformDataIntegrityException("error.msg.unknown.data.integrity.issue",
                    "Unknown data integrity issue with resource: " + e.getMostSpecificCause());
        }
    }

    /**
     * Method for Enabling the Active
     */
    @Transactional
    @Override
    public CommandProcessingResult enableAdHocQuery(Long adHocId) {
        try {
            /**
             * Checking the adHoc present in DB or not using id
             */
            final AdHoc adHoc = this.adHocRepository.getOne(adHocId);
            if (adHoc == null) { throw new AdHocNotFoundException(adHocId); }
            adHoc.enableActive();
            this.adHocRepository.save(adHoc);
            return new CommandProcessingResultBuilder().withEntityId(adHocId).build();

        } catch (final DataIntegrityViolationException e) {
            throw new PlatformDataIntegrityException("error.msg.unknown.data.integrity.issue",
                    "Unknown data integrity issue with resource: " + e.getMostSpecificCause());
        }
    }
}