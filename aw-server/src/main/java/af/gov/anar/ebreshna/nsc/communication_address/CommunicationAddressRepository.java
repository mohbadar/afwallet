package af.gov.anar.ebreshna.nsc.communication_address;

import af.gov.anar.ebreshna.configuration.billing.behaviour.BehaviourConfiguration;
import af.gov.anar.ebreshna.nsc.communication_address.CommunicationAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunicationAddressRepository extends JpaRepository<CommunicationAddress, Long>, RevisionRepository<CommunicationAddress, Long, Integer> {
}
