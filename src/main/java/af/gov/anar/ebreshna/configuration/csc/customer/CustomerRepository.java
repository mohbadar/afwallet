package af.gov.anar.ebreshna.configuration.csc.customer;

import af.gov.anar.ebreshna.configuration.csc.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
