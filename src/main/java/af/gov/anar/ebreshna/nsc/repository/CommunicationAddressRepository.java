package af.gov.anar.ebreshna.nsc.repository;

import af.gov.anar.ebreshna.nsc.model.CommunicationAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunicationAddressRepository extends JpaRepository<CommunicationAddress, Long> {
}
