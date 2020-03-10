package af.gov.anar.dck.useradministration.service;


import af.gov.anar.dck.useradministration.model.Permission;
import af.gov.anar.dck.useradministration.model.User;
import af.gov.anar.dck.useradministration.repository.PermissionRepository;
import af.gov.anar.dck.useradministration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionRepository permissionRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<Permission> findAll() {
		return permissionRepository.findAll();
	}

	@Override
	public List<Permission> findAllByUserAndEnv(User user, String envSlug) {
		System.out.println("**************** USER ID IS "+user.getId()+" AND USER ENVSLUG IS "+envSlug+"*****************");
		return permissionRepository.findAllByUserAndEnv(user.getId(), envSlug);
	}
}
