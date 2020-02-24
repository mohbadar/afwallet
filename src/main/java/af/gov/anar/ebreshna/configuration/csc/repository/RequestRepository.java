package af.gov.anar.ebreshna.configuration.csc.repository;

import af.gov.anar.ebreshna.configuration.csc.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
}
