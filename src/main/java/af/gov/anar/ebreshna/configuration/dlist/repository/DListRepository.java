package af.gov.anar.ebreshna.configuration.dlist.repository;

import af.gov.anar.ebreshna.configuration.dlist.model.DList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DListRepository extends JpaRepository<DList, Long> {
}
