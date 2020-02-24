package af.gov.anar.ebreshna.configuration.csc.repository;

import af.gov.anar.ebreshna.configuration.csc.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
