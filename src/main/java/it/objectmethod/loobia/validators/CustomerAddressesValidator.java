package it.objectmethod.loobia.validators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

import it.objectmethod.loobia.dto.CustomerAddressesDto;
import it.objectmethod.loobia.repository.AreaRepository;
import it.objectmethod.loobia.repository.CustomerAddressesRepository;
import it.objectmethod.loobia.repository.CustomerRepository;
import it.objectmethod.loobia.repository.MunicipalityRepository;
import it.objectmethod.loobia.repository.UserRepository;
import it.objectmethod.loobia.validator.rules.CustomerAddressesCityRule;
import it.objectmethod.loobia.validator.rules.CustomerAddressesInvoiceRule;
import it.objectmethod.loobia.validator.rules.UserPermissionsManipulationAddressRules;

@Component
public class CustomerAddressesValidator {

	public List<String> validateCustomerAddress(AreaRepository areRepo, CustomerRepository customerRepo,
			UserRepository userRepo, String email, CustomerAddressesDto customerAddressDto,
			MunicipalityRepository municipalityRepo, CustomerAddressesRepository customerAddressesRepo) {
		List<String> errors = new ArrayList<String>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("customerAddressDto", customerAddressDto);
		params.put("errList", errors);
		params.put("municipalityRepo", municipalityRepo);
		params.put("customerAddressesRepo", customerAddressesRepo);
		params.put("email", email);
		params.put("userRepo", userRepo);
		params.put("custRepo", customerRepo);
		params.put("areRepository", areRepo);

		new CustomerAddressesCityRule().validate(params);
		new UserPermissionsManipulationAddressRules().validate(params);
		new CustomerAddressesInvoiceRule().validate(params);

		return errors;
	}
}
