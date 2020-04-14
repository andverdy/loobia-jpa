package it.objectmethod.loobia.validator.rules;

import java.util.Map;

import it.objectmethod.loobia.dto.CustomerAddressesDto;
import it.objectmethod.loobia.repository.CustomerRepository;

public class CustomerAddressesReceiverRule implements IValidatorRule {

	@Override
	public void validate(Map<String, Object> paramsToValidate) {

		CustomerAddressesDto customAddressInsertDto = (CustomerAddressesDto) paramsToValidate.get("customerAddressDto");
		CustomerRepository customerRepo = (CustomerRepository) paramsToValidate.get("custRepo");

		if (customAddressInsertDto.getDestinatario() == null || customAddressInsertDto.getDestinatario().equals("")) {

			String customerExtractSocialReason = customerRepo
					.findRagioneSocialeById(customAddressInsertDto.getIdCliente());
			customAddressInsertDto.setDestinatario(customerExtractSocialReason);
		}

	}

}
