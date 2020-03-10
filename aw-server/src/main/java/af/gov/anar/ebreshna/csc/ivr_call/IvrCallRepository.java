package af.gov.anar.ebreshna.csc.ivr_call;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IvrCallRepository extends JpaRepository<IvrCall, Long>, RevisionRepository<IvrCall, Long, Integer> {
}
