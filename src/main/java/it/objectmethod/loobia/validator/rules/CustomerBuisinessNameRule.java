package it.objectmethod.loobia.validator.rules;

import java.util.List;
import java.util.Map;

import it.objectmethod.loobia.entity.Customer;

@SuppressWarnings("unchecked")
public class CustomerBuisinessNameRule implements IValidatorRule {

	@Override
	public void validate(Map<String, Object> paramsToValidate) {

		List<String> errors = (List<String>) paramsToValidate.get("errList");
		Customer customer = (Customer) paramsToValidate.get("customer");
		if (customer.getRagioneSociale().equals("") || customer.getRagioneSociale() == null) {
			errors.add("Il campo Ragione Sociale non può essere vuoto!");
		}
	}

}
