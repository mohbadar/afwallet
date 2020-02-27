package af.gov.anar.ebreshna.nsc.repository;

import af.gov.anar.ebreshna.nsc.model.ApplicantInfoDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantInfoDetailRepository extends JpaRepository<ApplicantInfoDetail, Long> {
}
