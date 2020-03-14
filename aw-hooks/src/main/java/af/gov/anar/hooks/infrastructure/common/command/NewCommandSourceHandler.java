
package af.gov.anar.hooks.infrastructure.common.command;


import af.gov.anar.lang.data.CommandProcessingResult;

public interface NewCommandSourceHandler {

    CommandProcessingResult processCommand(JsonCommand command);
}