package af.gov.anar.dck.useradministration.service;

import af.gov.anar.dck.useradministration.model.Permission;
import af.gov.anar.dck.useradministration.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface PermissionService  {	   
	public List findAll();
	public List<Permission> findAllByUserAndEnv(User user, String envSlug);
}
