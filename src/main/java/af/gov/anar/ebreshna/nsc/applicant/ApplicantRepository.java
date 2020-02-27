package af.gov.anar.ebreshna.nsc.applicant;

import af.gov.anar.ebreshna.nsc.applicant.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
}
