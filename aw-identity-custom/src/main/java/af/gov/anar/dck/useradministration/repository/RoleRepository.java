package af.gov.anar.dck.useradministration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import af.gov.anar.dck.useradministration.model.Role;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String name);
	public List<Role> findByEnvSlug(String envSlug);
}
