package it.objectmethod.loobia.validator.rules;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.objectmethod.loobia.entity.Customer;

@SuppressWarnings("unchecked")
public class CustomerVatRule implements IValidatorRule {

	@Override
	public void validate(Map<String, Object> paramsToValidate) {
		List<String> errors = (List<String>) paramsToValidate.get("errList");
		Customer customer = (Customer) paramsToValidate.get("customer");
		if (customer.getPartitaIva() != null && pIvaValidate(customer.getPartitaIva())
				|| customer.getPartitaIva().isEmpty()) {
		} else {
			errors.add("Formato partita iva errato!");
		}
	}

	private boolean pIvaValidate(String pIva) {
		String regex = "[0-9]{11}";

		Pattern p = Pattern.compile(regex);

		Matcher m = p.matcher(pIva);
		if (m.matches()) {
			return true;
		} else {
			return false;
		}

	}

}
