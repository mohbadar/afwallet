package af.gov.anar.ebreshna.configuration.billing.process_behaviour_link;

import af.gov.anar.ebreshna.configuration.billing.behaviour.BehaviourConfiguration;
import af.gov.anar.ebreshna.configuration.billing.process_behaviour_link.ProcessBehaviourLinkConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessBehaviourLinkRepository extends JpaRepository<ProcessBehaviourLinkConfiguration, Long>, RevisionRepository<ProcessBehaviourLinkConfiguration, Long, Integer> {
}
