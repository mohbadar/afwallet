package af.asr.csc.api;

import af.gov.anar.lib.logger.Logger;
import af.gov.anar.lib.logger.appender.ConsoleAppender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CustomerRestController {

    private final ConsoleAppender logger = new ConsoleAppender();
//    private final CustomerService customerService;
//    private final FieldValueValidator fieldValueValidator;
//    private final TaskService taskService;
//    private final Environment environment;

}
