
package af.asr.notification.service.externalServiceClients;

import af.asr.customer.domain.Customer;
import af.asr.customer.exception.CustomerNotFoundException;
import af.asr.customer.manager.CustomerManager;
import af.asr.customer.util.ServiceConstants;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
	
	private final Logger logger;
	private final CustomerManager customerManager;
	
	@Autowired
	public CustomerService(@Qualifier(ServiceConstants.LOGGER_NAME) final Logger logger,
	                       final CustomerManager customerManager) {
		super();
		this.logger = logger;
		this.customerManager = customerManager;
	}
	
	public Optional<Customer> findCustomer(final String customerIdentifier) {
		try {
			final Customer customer = this.customerManager.findCustomer(customerIdentifier.replaceAll("\"", ""));
			return Optional.of(customer);
		} catch (final CustomerNotFoundException cnfex) {
			this.logger.warn("Customer {} not found.", customerIdentifier.replaceAll("\"", ""));
		}
		return Optional.empty();
	}
}
