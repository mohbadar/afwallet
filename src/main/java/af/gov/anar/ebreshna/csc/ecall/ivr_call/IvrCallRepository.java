package af.gov.anar.ebreshna.csc.ecall.ivr_call;

import af.gov.anar.ebreshna.csc.complaint.complaint_modification.ComplaintModification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IvrCallRepository extends JpaRepository<IvrCall, Long>, RevisionRepository<IvrCall, Long, Integer> {
}
