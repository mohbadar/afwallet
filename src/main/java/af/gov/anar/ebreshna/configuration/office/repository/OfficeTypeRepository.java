package af.gov.anar.ebreshna.configuration.office.repository;

import af.gov.anar.ebreshna.configuration.office.model.OfficeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeTypeRepository extends JpaRepository<OfficeType, Long> {
}
