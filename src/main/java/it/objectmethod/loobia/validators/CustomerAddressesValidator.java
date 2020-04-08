package it.objectmethod.loobia.validators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import it.objectmethod.loobia.entity.CustomerAddresses;
import it.objectmethod.loobia.repository.MunicipalityRepository;
import it.objectmethod.loobia.validator.rules.CustomerAddressesRule;

@Component
public class CustomerAddressesValidator {

	public List<String> validateCustomerAddress(CustomerAddresses customerAddress, MunicipalityRepository municipalityRepo) {
		List<String> errors = new ArrayList<String>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("customerAddress", customerAddress);
		params.put("errList", errors);
		params.put("municipalityRepo", municipalityRepo);

		new CustomerAddressesRule().validate(params);

		return errors;
	}
}
