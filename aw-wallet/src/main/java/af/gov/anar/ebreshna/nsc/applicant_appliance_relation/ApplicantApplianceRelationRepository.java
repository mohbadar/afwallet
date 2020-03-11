package af.gov.anar.ebreshna.nsc.applicant_appliance_relation;

import af.gov.anar.ebreshna.configuration.billing.behaviour.BehaviourConfiguration;
import af.gov.anar.ebreshna.nsc.applicant_appliance_relation.ApplicantApplianceRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantApplianceRelationRepository extends JpaRepository<ApplicantApplianceRelation, Long> , RevisionRepository<ApplicantApplianceRelation, Long, Integer> {
}
