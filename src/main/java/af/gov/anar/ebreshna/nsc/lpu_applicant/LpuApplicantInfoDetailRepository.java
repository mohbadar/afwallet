package af.gov.anar.ebreshna.nsc.lpu_applicant;

import af.gov.anar.ebreshna.nsc.lpu_applicant.LpuApplicantInfoDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LpuApplicantInfoDetailRepository extends JpaRepository<LpuApplicantInfoDetail, Long> {
}
