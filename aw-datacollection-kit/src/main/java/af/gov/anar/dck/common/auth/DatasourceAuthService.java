package af.gov.anar.dck.common.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import af.gov.anar.dck.datasource.model.Datasource;
import af.gov.anar.dck.datasource.service.DatasourceService;

import java.util.List;

/**
 * @author Jalil haidari
 * 
 *         Class to check if the user has the authority of the action for which
 *         he has requested.
 * @PreAuthorized annotation of spring-boot is used to check if particular
 *                authority is present in Principal object for current user.
 *
 */

@Service
public class DatasourceAuthService {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DatasourceService datasourceService;

    @PreAuthorize("hasAuthority('DATASOURCE_CREATE')")
    public Datasource create(Datasource datasource) {
        logger.info("Entry DatasourceService>create() - POST");
        return datasourceService.create(datasource);
    }

    @PreAuthorize("hasAuthority('DATASOURCE_DELETE')")
    public Datasource delete(Long id) {
        logger.info("Entry DatasourceService>delete() - DELETE");
        return datasourceService.delete(id);
    }

    @PreAuthorize("hasAuthority('DATASOURCE_LIST')")
    public List findAll() {
        logger.info("Entry DatasourceService>findAll() - GET");
        return datasourceService.findAll();
    }

    @PreAuthorize("hasAuthority('DATASOURCE_LIST')")
    public List findAllByEnv(String envSlug) {
        logger.info("Entry DatasourceService>findAllByEnv() - GET");
        return datasourceService.findAllByEnv(envSlug);
    }

    @PreAuthorize("hasAuthority('DATASOURCE_VIEW')")
    public Datasource findById(Long id) {
        logger.info("Entry DatasourceService>findById() - GET");
        return datasourceService.findById(id);
    }

    @PreAuthorize("hasAuthority('DATASOURCE_EDIT')")
    public boolean update(Long id, Datasource datasource) {
        logger.info("Entry DatasourceService>update() - PUT");
        return datasourceService.update(id, datasource);
    }

}
