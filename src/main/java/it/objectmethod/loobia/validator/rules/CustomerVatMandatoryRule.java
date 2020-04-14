package it.objectmethod.loobia.validator.rules;

import java.util.List;
import java.util.Map;

import it.objectmethod.loobia.entity.Customer;

@SuppressWarnings("unchecked")
public class CustomerVatMandatoryRule implements IValidatorRule {

	@Override
	public void validate(Map<String, Object> paramsToValidate) {
		List<String> errors = (List<String>) paramsToValidate.get("errList");
		Customer customer = (Customer) paramsToValidate.get("customer");
		if (customer.getTipo() != null && customer.getTipo().equals("PG") && customer.getPartitaIva().isEmpty()) {
			errors.add("Il Campo Partita Iva è obbligatorio!");
		} else if (customer.getTipo().isEmpty()) {
			errors.add("Campo Tipo Mancante!");
		}

	}

}
