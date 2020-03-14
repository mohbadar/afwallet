
package af.gov.anar.hooks.hook.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HookTemplateRepository extends
		JpaRepository<HookTemplate, Long>,
		JpaSpecificationExecutor<HookTemplate> {

	@Query("select template from HookTemplate template where template.name = :name")
	HookTemplate findOne(@Param("name") String name);
}
