package af.gov.anar.ebreshna.nsc.applicant_appliance_relation;

import af.gov.anar.ebreshna.nsc.applicant_appliance_relation.ApplicantApplianceRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantApplianceRelationRepository extends JpaRepository<ApplicantApplianceRelation, Long> {
}
