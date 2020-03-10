package af.gov.anar.dck.datasource.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import af.gov.anar.dck.common.auth.DatasourceAuthService;
import af.gov.anar.dck.datasource.model.Datasource;
import af.gov.anar.dck.useradministration.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping({ "/api/datasource" })
public class DatasourceController {

    Logger logger = LoggerFactory.getLogger(DatasourceController.class);

    @Autowired
    private DatasourceAuthService datasourceAuthService;
    @Autowired
    private UserService userService;

    ObjectMapper mapper = new ObjectMapper();

    @PostMapping
    public Datasource create(@Valid @RequestBody Datasource datasource) {
        logger.info("Entry DatasourceController>create() - POST");
        datasource.setEnvSlug(userService.getCurrentEnv());
        return datasourceAuthService.create(datasource);
    }

    @GetMapping(path = { "/{id}" })
    public Datasource findOne(@PathVariable("id") Long id) {
        logger.info("Entry DatasourceController>findOne() - GET");
        return datasourceAuthService.findById(id);
    }

    @PutMapping(path = { "/{id}" })
    public boolean update(@PathVariable("id") Long id, @RequestBody Datasource datasource) {
        logger.info("Entry DatasourceController>update() - PUT");
        datasource.setEnvSlug(userService.getCurrentEnv());
        return datasourceAuthService.update(id, datasource);
    }

    @GetMapping
    public List findAll() {
        logger.info("Entry DatasourceController>findAll() - GET");
        String envSlug = userService.getCurrentEnv();
        return datasourceAuthService.findAllByEnv(envSlug);
    }

    // @RequestMapping(value="/{id}/xml", method = RequestMethod.GET)
    // public String getDatasourceContent(@PathVariable("id") Long id) throws
    // IOException {
    // return datasourceService.findById(id).getXmlContent();
    // }
}