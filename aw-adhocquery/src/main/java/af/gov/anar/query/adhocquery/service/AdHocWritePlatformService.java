
package af.gov.anar.query.adhocquery.service;


import af.gov.anar.query.infrastructure.common.command.JsonCommand;
import af.gov.anar.lang.data.CommandProcessingResult;

public interface AdHocWritePlatformService {

    CommandProcessingResult createAdHocQuery(JsonCommand command);

    CommandProcessingResult updateAdHocQuery(Long adHocId, JsonCommand command);
 
    CommandProcessingResult deleteAdHocQuery(Long adHocId);

    CommandProcessingResult disableAdHocQuery(Long adHocId);

    CommandProcessingResult enableAdHocQuery(Long adHocId);
}