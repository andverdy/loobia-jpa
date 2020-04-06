package it.objectmethod.loobia.validators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import it.objectmethod.loobia.entity.Customer;
import it.objectmethod.loobia.validator.rules.CustomerFiscalCodeRule;
import it.objectmethod.loobia.validator.rules.CustomerSocialReasonRule;

@Component
public class CustomerValidator {

	public List<String> validateCustomer(Customer customer) {
		List<String> errors = new ArrayList<String>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("customer", customer);
		params.put("errList", errors);

		new CustomerSocialReasonRule().validate(params);
		new CustomerFiscalCodeRule().validate(params);

		return errors;
	}

}
