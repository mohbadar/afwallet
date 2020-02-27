package af.gov.anar.ebreshna.nsc.applicant_info_detail;

import af.gov.anar.ebreshna.nsc.applicant_info_detail.ApplicantInfoDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantInfoDetailRepository extends JpaRepository<ApplicantInfoDetail, Long> {
}
