package af.gov.anar.ebreshna.configuration.csc.repository;

import af.gov.anar.ebreshna.configuration.csc.model.RequestType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestTypeRepository extends JpaRepository<RequestType, Long> {
}
