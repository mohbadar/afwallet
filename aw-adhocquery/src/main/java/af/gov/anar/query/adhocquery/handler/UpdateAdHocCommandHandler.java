
package af.gov.anar.query.adhocquery.handler;

import af.gov.anar.query.adhocquery.service.AdHocWritePlatformService;
import af.gov.anar.query.infrastructure.common.annotation.CommandType;
import af.gov.anar.query.infrastructure.common.command.JsonCommand;
import af.gov.anar.query.infrastructure.common.command.NewCommandSourceHandler;
import af.gov.anar.lang.data.CommandProcessingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@CommandType(entity = "ADHOC", action = "UPDATE")
public class UpdateAdHocCommandHandler implements NewCommandSourceHandler {

    private final AdHocWritePlatformService writePlatformService;

    @Autowired
    public UpdateAdHocCommandHandler(final AdHocWritePlatformService writePlatformService) {
        this.writePlatformService = writePlatformService;
    }

    @Transactional
    @Override
    public CommandProcessingResult processCommand(final JsonCommand command) {

        final Long adHocId = command.entityId();
        return this.writePlatformService.updateAdHocQuery(adHocId, command);
    }
}