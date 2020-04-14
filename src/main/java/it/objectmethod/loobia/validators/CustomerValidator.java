package it.objectmethod.loobia.validators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.objectmethod.loobia.entity.Customer;
import it.objectmethod.loobia.repository.MunicipalityRepository;
import it.objectmethod.loobia.validator.rules.CustomerBirthplaceRule;
import it.objectmethod.loobia.validator.rules.CustomerTaxCodeRule;
import it.objectmethod.loobia.validator.rules.CustomerBuisinessNameRule;
import it.objectmethod.loobia.validator.rules.CustomerVatMandatoryRule;
import it.objectmethod.loobia.validator.rules.CustomerVatRule;

@Component
public class CustomerValidator {
	
	@Autowired
	private MunicipalityRepository municRepo;

	public List<String> validateCustomer(Customer customer) {
		List<String> errors = new ArrayList<String>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("customer", customer);
		params.put("errList", errors);
		params.put("municRepo", municRepo);

		new CustomerBuisinessNameRule().validate(params);
		new CustomerTaxCodeRule().validate(params);
		new CustomerVatMandatoryRule().validate(params);
		new CustomerVatRule().validate(params);
		new CustomerBirthplaceRule().validate(params);

		return errors;
	}

}
