//package it.objectmethod.loobia.validator.rules;
//
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.web.server.ResponseStatusException;
//
//import it.objectmethod.loobia.entity.Order;
//import it.objectmethod.loobia.entity.Customer;
//import it.objectmethod.loobia.repository.CustomerRepository;
//
//@SuppressWarnings("unchecked")
//public class CustomerOrderRule implements IValidatorRule {
//
//	@Override
//	public void validate(Map<String, Object> paramsToValidate) {
//		List<String> errors = (List<String>) paramsToValidate.get("errList");
//		Order order = (Order) paramsToValidate.get("order");
//		
//		CustomerRepository customerRepository = (CustomerRepository) paramsToValidate.get("customerRepo");
//
//		Customer customer;
//		try {
//			customer = customerRepository.findById(order.getCustomerOrder().getId()).get();
//		} catch (Exception e) {
//			errors.add("Il cliente inserito non esiste!");
//		}
//
//	}
//
//}
