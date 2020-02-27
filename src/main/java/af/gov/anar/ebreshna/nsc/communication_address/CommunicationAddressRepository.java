package af.gov.anar.ebreshna.nsc.communication_address;

import af.gov.anar.ebreshna.nsc.communication_address.CommunicationAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunicationAddressRepository extends JpaRepository<CommunicationAddress, Long> {
}
