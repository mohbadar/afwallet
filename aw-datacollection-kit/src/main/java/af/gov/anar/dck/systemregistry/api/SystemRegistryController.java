package af.gov.anar.dck.systemregistry.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import af.gov.anar.dck.systemregistry.model.SystemRegistry;
import af.gov.anar.dck.systemregistry.service.SystemRegistryService;
import af.gov.anar.dck.useradministration.service.UserService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping({ "/api/sys_reg" })
public class SystemRegistryController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SystemRegistryService systemRegistryService;

    @Autowired
    private UserService userService;

    ObjectMapper mapper = new ObjectMapper();

    @PostMapping
    public SystemRegistry create(@Valid @RequestBody SystemRegistry systemRegistry) {
        logger.info("Entry SystemRegistry>create() - POST");
        systemRegistry.setEnvSlug(userService.getCurrentEnv());
        return systemRegistryService.create(systemRegistry);
    }

    @GetMapping(path = { "/{id}" })
    public SystemRegistry findOne(@PathVariable("id") Long id) {
        logger.info("Entry SystemRegistry>findOne() - GET");
        return systemRegistryService.findById(id);
    }

    @PutMapping(path = { "/{id}" })
    public boolean update(@PathVariable("id") Long id, @RequestBody SystemRegistry systemRegistry) {
        logger.info("Entry SystemRegistry>update() - PUT");
        return systemRegistryService.update(id, systemRegistry);
    }

    @GetMapping
    public List findAll() {
        logger.info("Entry SystemRegistry>findAll() - GET");
        String envSlug = userService.getCurrentEnv();
        return systemRegistryService.findAllByEnv(envSlug);
    }

    @RequestMapping(value = "/{id}/xml", method = RequestMethod.GET)
    public String getFormContent(@PathVariable("id") Long id) throws IOException {
        logger.info("Entry SystemRegistry>getFormContent() - GET");
        return systemRegistryService.findById(id).getContent();
    }
}
