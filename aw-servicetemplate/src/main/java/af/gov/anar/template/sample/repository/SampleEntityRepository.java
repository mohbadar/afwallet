package af.gov.anar.template.sample.repository;

import af.gov.anar.template.sample.model.SampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleEntityRepository extends JpaRepository<SampleEntity, Long> {
}
