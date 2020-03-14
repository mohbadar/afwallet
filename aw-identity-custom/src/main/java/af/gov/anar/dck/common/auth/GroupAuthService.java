package af.gov.anar.dck.common.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import af.gov.anar.dck.useradministration.model.Group;
import af.gov.anar.dck.useradministration.service.GroupService;

import java.util.List;

// import java.util.Optional;

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
public class GroupAuthService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private GroupService groupService;

	@PreAuthorize("hasAuthority('GROUP_CREATE')")
	public Group create(Group group) {
		logger.info("Entry GroupAuthService>findAllByGroup - POST");
		return groupService.create(group);
	}

	@PreAuthorize("hasAuthority('GROUP_DELETE')")
	public List<Group> delete(Long id) {
		logger.info("Entry GroupAuthService>findAllByGroup - DELETE");
		return groupService.delete(id);

	}

	@PreAuthorize("hasAuthority('GROUP_LIST')")
	public List<Group> findAll() {
		logger.info("Entry GroupAuthService>findAllByGroup - GET");
		return groupService.findAll();
	}

	@PreAuthorize("hasAuthority('GROUP_LIST')")
	public List<Group> findAllByEnv(String envSlug) {
		logger.info("Entry GroupAuthService>findAllByEnv - GET");
		return groupService.findAllByEnv(envSlug);
	}

	@PreAuthorize("hasAuthority('GROUP_VIEW')")
	public Group findById(Long id) {
		logger.info("Entry GroupAuthService>findAllByGroup - GET");
		return groupService.findById(id);
	}

	@PreAuthorize("hasAuthority('GROUP_EDIT')")
	public boolean update(Long id, Group group) {

		logger.info("Entry GroupAuthService>findAllByGroup - PUT");
		return groupService.update(id, group);
	}

}
