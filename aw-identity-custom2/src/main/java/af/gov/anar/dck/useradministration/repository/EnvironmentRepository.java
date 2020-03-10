package af.gov.anar.dck.useradministration.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import af.gov.anar.dck.useradministration.model.Environment;

@Repository
public interface EnvironmentRepository extends JpaRepository<Environment, Long> {

    public Environment findBySlug(String slug);
    public Environment findBySecretKey(String secretKey);
}