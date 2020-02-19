package af.gov.anar.ebreshna.common.dlist.repository;

import af.gov.anar.ebreshna.common.dlist.model.DList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DListRepository extends JpaRepository<DList, Long> {
}
