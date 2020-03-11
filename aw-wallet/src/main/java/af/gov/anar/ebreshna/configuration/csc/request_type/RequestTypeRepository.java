package af.gov.anar.ebreshna.configuration.csc.request_type;

import af.gov.anar.ebreshna.configuration.csc.request_type.RequestType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestTypeRepository extends JpaRepository<RequestType, Long> {
}
