package it.objectmethod.loobia.validator.rules;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.objectmethod.loobia.entity.Customer;

@SuppressWarnings("unchecked")
public class CustomerTaxCodeRule implements IValidatorRule {

	@Override
	public void validate(Map<String, Object> paramsToValidate) {

		List<String> errors = (List<String>) paramsToValidate.get("errList");
		Customer customer = (Customer) paramsToValidate.get("customer");
		if (customer.getCodiceFiscale() != null && !(taxCodeValidate(customer.getCodiceFiscale()))) {
			errors.add("Formato codice fiscale errato!");
		}
	}

	private boolean taxCodeValidate(String taxCode) {
		String regex = "^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(taxCode);
		return m.matches();

	}

}
