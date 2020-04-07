package it.objectmethod.loobia.validators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import it.objectmethod.loobia.entity.Customer;
import it.objectmethod.loobia.repository.MunicipalityRepository;
import it.objectmethod.loobia.validator.rules.CustomerBirthplaceRule;
import it.objectmethod.loobia.validator.rules.CustomerFiscalCodeRule;
import it.objectmethod.loobia.validator.rules.CustomerSocialReasonRule;
import it.objectmethod.loobia.validator.rules.CustomerTypeRule;
import it.objectmethod.loobia.validator.rules.CustomerVatRule;

@Component
public class CustomerValidator {

	public List<String> validateCustomer(Customer customer, MunicipalityRepository municipalityRepo) {
		List<String> errors = new ArrayList<String>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("customer", customer);
		params.put("errList", errors);
		params.put("municRepo", municipalityRepo);

		new CustomerSocialReasonRule().validate(params);
		new CustomerFiscalCodeRule().validate(params);
		new CustomerTypeRule().validate(params);
		new CustomerVatRule().validate(params);
		new CustomerBirthplaceRule().validate(params);

		return errors;
	}

}
