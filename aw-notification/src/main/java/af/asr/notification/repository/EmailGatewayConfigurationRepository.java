
package af.asr.notification.repository;

import af.asr.notification.model.EmailGatewayConfigurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailGatewayConfigurationRepository extends JpaRepository<EmailGatewayConfigurationEntity, Long> {
	Optional<EmailGatewayConfigurationEntity> findByIdentifier(String identifier);
	
	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN 'true' ELSE 'false' END FROM EmailGatewayConfigurationEntity c WHERE c.identifier = :identifier")
	Boolean existsByIdentifier(@Param("identifier") final String identifier);
	
	@Query("SELECT entity FROM EmailGatewayConfigurationEntity entity WHERE entity.identifier='DEFAULT'")
	Optional<EmailGatewayConfigurationEntity> defaultGateway();
	
	void deleteEmailGatewayConfigurationEntityByIdentifier(String identifier);
	
}
