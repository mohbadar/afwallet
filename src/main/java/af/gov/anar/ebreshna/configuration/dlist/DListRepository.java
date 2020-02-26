package af.gov.anar.ebreshna.configuration.dlist;

import af.gov.anar.ebreshna.configuration.dlist.DList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DListRepository extends JpaRepository<DList, Long> {
}
