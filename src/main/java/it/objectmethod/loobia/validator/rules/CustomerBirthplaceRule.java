package it.objectmethod.loobia.validator.rules;

import java.util.List;
import java.util.Map;

import it.objectmethod.loobia.entity.Customer;
import it.objectmethod.loobia.entity.Municipality;
import it.objectmethod.loobia.repository.MunicipalityRepository;

@SuppressWarnings("unchecked")
public class CustomerBirthplaceRule implements IValidatorRule {

	@Override
	public void validate(Map<String, Object> paramsToValidate) {
		List<String> errors = (List<String>) paramsToValidate.get("errList");
		Customer customer = (Customer) paramsToValidate.get("customer");
		MunicipalityRepository munic = (MunicipalityRepository) paramsToValidate.get("municRepo");
		Municipality municipality = munic.findByNome(customer.getComuneNascita());

		if (customer.getComuneNascita() != null) {
			if (municipality == null || (!customer.getProvinciaNascita().equals(municipality.getProvincia()))) {
				errors.add("Le informazioni relative al luogo di nascita sono errate!");
			}
		}

	}

}
