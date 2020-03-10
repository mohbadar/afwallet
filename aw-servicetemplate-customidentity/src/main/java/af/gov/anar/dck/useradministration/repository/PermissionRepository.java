package af.gov.anar.dck.useradministration.repository;

import af.gov.anar.dck.useradministration.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
	Permission findByName(String name);

	@Query(value = "select permission.id, permission.name, permission.description, permission.active from user_tbl inner join user_group on user_group.user_id = user_tbl.id inner join group_tbl on group_tbl.id = user_group.group_id inner join group_role on group_role.group_id = user_group.group_id inner join role_permission on role_permission.role_id = group_role.role_id inner join permission on permission.id = role_permission.permission_id where user_tbl.id=?1 and group_tbl.env_slug=?2", nativeQuery = true)
    List<Permission> findAllByUserAndEnv(Long userId, String envSlug);
}
