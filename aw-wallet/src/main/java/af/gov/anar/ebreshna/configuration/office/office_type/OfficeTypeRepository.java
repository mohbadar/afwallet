package af.gov.anar.ebreshna.configuration.office.office_type;

import af.gov.anar.ebreshna.configuration.office.office_type.OfficeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeTypeRepository extends JpaRepository<OfficeType, Long> {
}
