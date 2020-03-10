package af.gov.anar.dck.systemregistry.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import af.gov.anar.dck.systemregistry.model.SystemRegistry;

import java.util.List;

public interface SystemRegistryRepository extends JpaRepository<SystemRegistry, Long>  {
	public List<SystemRegistry> findByEnvSlug(String envSlug);
}
 