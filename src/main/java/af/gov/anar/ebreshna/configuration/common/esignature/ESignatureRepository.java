package af.gov.anar.ebreshna.configuration.common.esignature;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ESignatureRepository extends JpaRepository<ESignature, Long>, RevisionRepository<ESignature, Long, Integer> {

    ESignature findByUserId(String userId);
}
