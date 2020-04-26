package it.objectmethod.loobia.validator.rules;

import java.util.List;
import java.util.Map;
import it.objectmethod.loobia.dto.OrderDto;
import it.objectmethod.loobia.entity.Customer;
import it.objectmethod.loobia.repository.CustomerRepository;

@SuppressWarnings("unchecked")
public class CustomerOrderRule implements IValidatorRule {

	@Override
	public void validate(Map<String, Object> paramsToValidate) {
		List<String> errors = (List<String>) paramsToValidate.get("errList");
		OrderDto orderDto = (OrderDto) paramsToValidate.get("orderDto");

		CustomerRepository customerRepository = (CustomerRepository) paramsToValidate.get("customerRepo");

		Customer customer;
		try {
			customer = customerRepository.findById(orderDto.getIdCliente()).get();
		} catch (Exception e) {
			errors.add("Il cliente inserito non esiste!");
		}

	}

}
