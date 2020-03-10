
package af.gov.anar.hooks.hook.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HookRepository extends JpaRepository<Hook, Long>,
		JpaSpecificationExecutor<Hook> {

	@Query("select hook from Hook hook inner join hook.events event where event.entityName = :entityName and event.actionName = :actionName and hook.isActive = true")
	List<Hook> findAllHooksListeningToEvent(
            @Param("entityName") String entityName,
            @Param("actionName") String actionName);

	@Query("select hook from Hook hook where hook.template.id = :templateId ")
	Hook findOneByTemplateId(@Param("templateId") Long templateId);

}
