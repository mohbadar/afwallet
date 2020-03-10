package af.gov.anar.dck.common.api;


import af.gov.anar.dck.form.service.FormService;
import af.gov.anar.dck.instance.service.InstanceService;
import af.gov.anar.dck.useradministration.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import af.gov.anar.dck.infrastructure.logger.Loggable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping({ "/api/dashboards" })
public class DashboardController {

    Logger logger = LoggerFactory.getLogger(DashboardController.class);

    @Autowired
    private FormService formService;

    @Autowired
    private UserService userService;

    @Autowired
    private InstanceService instanceService;

    ObjectMapper mapper = new ObjectMapper();

    @Loggable
    @RequestMapping(value = "/summary", method = RequestMethod.GET)
    public Map<String, Object> summary() {
        String currentEnv = userService.getCurrentEnv();
        Map<String, Object> summary = new HashMap<>();
        summary.put("total_active_forms", formService.countByEnvSlugAndActive(currentEnv, true));
        summary.put("total_active_instances", instanceService.countByEnvSlugAndActive(currentEnv, true));
        summary.put("total_active_users", userService.countByEnvSlugAndActive(currentEnv, true));

        return summary;
    }
}