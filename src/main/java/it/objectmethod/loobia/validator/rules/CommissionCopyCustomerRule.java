package it.objectmethod.loobia.validator.rules;

import java.util.List;
import java.util.Map;

import it.objectmethod.loobia.entity.CommissionCopy;
import it.objectmethod.loobia.entity.Customer;
import it.objectmethod.loobia.repository.CustomerRepository;

@SuppressWarnings("unchecked")
public class CommissionCopyCustomerRule implements IValidatorRule {

	@Override
	public void validate(Map<String, Object> paramsToValidate) {
		List<String> errors = (List<String>) paramsToValidate.get("errList");
		CommissionCopy cC = (CommissionCopy) paramsToValidate.get("commissionCopy");
		CustomerRepository customerRepository = (CustomerRepository) paramsToValidate.get("customerRepo");

		if (cC != null && cC.getIdCliente() != null) {
			if (customerRepository != null) {
				Customer customer;
				try {
					customer = customerRepository.findById(cC.getIdCliente()).get();
				} catch (Exception e) {
					errors.add("Il cliente inserito non esiste!");
				}

			}
		}

	}

}
