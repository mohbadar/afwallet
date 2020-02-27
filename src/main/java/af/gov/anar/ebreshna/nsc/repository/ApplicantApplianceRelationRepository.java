package af.gov.anar.ebreshna.nsc.repository;

import af.gov.anar.ebreshna.nsc.model.ApplicantApplianceRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantApplianceRelationRepository extends JpaRepository<ApplicantApplianceRelation, Long> {
}
