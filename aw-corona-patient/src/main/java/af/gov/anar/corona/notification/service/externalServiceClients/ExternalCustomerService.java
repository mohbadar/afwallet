package af.gov.anar.corona.notification.service.externalServiceClients;//
//package af.gov.anar.corona.notification.service.externalServiceClients;
//
//import af.gov.anar.corona.customer.domain.Customer;
//import af.gov.anar.corona.customer.exception.CustomerNotFoundException;
//import af.gov.anar.corona.customer.manager.CustomerManager;
//import af.gov.anar.corona.customer.util.ServiceConstants;
//import af.gov.anar.corona.notification.ServiceConstants;
//import org.slf4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class ExternalCustomerService {
//
//	private final Logger logger;
//	private final CustomerManager customerManager;
//
//	@Autowired
//	public ExternalCustomerService(@Qualifier(ServiceConstants.LOGGER_NAME) final Logger logger,
//								   final CustomerManager customerManager) {
//		super();
//		this.logger = logger;
//		this.customerManager = customerManager;
//	}
//
//	public Optional<Customer> findCustomer(final String customerIdentifier) {
//		try {
//			final Customer customer = this.customerManager.findCustomer(customerIdentifier.replaceAll("\"", ""));
//			return Optional.of(customer);
//		} catch (final CustomerNotFoundException cnfex) {
//			this.logger.warn("Customer {} not found.", customerIdentifier.replaceAll("\"", ""));
//		}
//		return Optional.empty();
//	}
//}
