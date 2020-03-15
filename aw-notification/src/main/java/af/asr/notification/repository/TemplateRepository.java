
package af.asr.notification.repository;

import af.asr.notification.model.TemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TemplateRepository extends JpaRepository<TemplateEntity, Long> {
	Optional<TemplateEntity> findByTemplateIdentifier(String identifier);
	
	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN 'true' ELSE 'false' END FROM TemplateEntity c WHERE c.templateIdentifier = :template_identifier")
	Boolean existsByTemplateIdentifier(@Param("template_identifier") final String identifier);
	
}
