package af.gov.anar.ebreshna.nsc.repository;

import af.gov.anar.ebreshna.nsc.model.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
}
