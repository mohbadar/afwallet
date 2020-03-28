
package af.gov.anar.corona.notification.repository;

import af.gov.anar.corona.notification.model.SMSGatewayConfigurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SMSGatewayConfigurationRepository extends JpaRepository<SMSGatewayConfigurationEntity, Long> {
	Optional<SMSGatewayConfigurationEntity> findByIdentifier(String identifier);
	
	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN 'true' ELSE 'false' END FROM SMSGatewayConfigurationEntity c WHERE c.identifier = :identifier")
	Boolean existsByIdentifier(@Param("identifier") final String identifier);
	
	@Query("SELECT entity FROM SMSGatewayConfigurationEntity entity WHERE entity.identifier='DEFAULT'")
	Optional<SMSGatewayConfigurationEntity> defaultGateway();
	
	void deleteSMSGatewayConfigurationEntityByIdentifier(String identifier);
}
