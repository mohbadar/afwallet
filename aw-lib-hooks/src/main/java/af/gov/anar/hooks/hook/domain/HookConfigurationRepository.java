
package af.gov.anar.hooks.hook.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HookConfigurationRepository extends
		JpaRepository<HookConfiguration, Long>,
		JpaSpecificationExecutor<HookConfiguration> {

	@Query("select config.fieldValue from HookConfiguration config where config.hook.id = :hookId and config.fieldName = :fieldName")
	String findOneByHookIdAndFieldName(@Param("hookId") Long hookId,
                                       @Param("fieldName") String fieldName);

}
