package af.gov.anar.dck.common.auth;

import af.gov.anar.dck.useradministration.model.Environment;
import af.gov.anar.dck.useradministration.service.EnvironmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnvironmentAuthService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EnvironmentService environmentService;

	@PreAuthorize("hasAuthority('ENV_CREATE')")
	public Environment create(Environment environment) {
		logger.info("Entry EnvironmentService>create() - POST");
		return environmentService.create(environment);
	}

	@PreAuthorize("hasAuthority('ENV_DELETE')")
	public Environment delete(Long id) {
		logger.info("Entry EnvironmentService>create() - DELETE");
		return environmentService.delete(id);
	}

	@PreAuthorize("hasAuthority('ENV_LIST')")
	public List findAll() {
		logger.info("Entry EnvironmentService>findAll() - GET");
		return environmentService.findAll();
	}

	@PreAuthorize("hasAuthority('ENV_VIEW')")
	public Environment findById(Long id) {
		logger.info("Entry EnvironmentService>findById() - GET");
		return environmentService.findById(id);
	}

	@PreAuthorize("hasAuthority('ENV_EDIT')")
	public boolean update(Long id, Environment environment) {
		logger.info("Entry EnvironmentService>update() - PUT");
		return environmentService.update(id, environment);
	}

}
