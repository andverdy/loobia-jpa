package it.objectmethod.loobia.validator.rules;

import java.util.List;
import java.util.Map;

import it.objectmethod.loobia.dto.CustomerAddressesDto;
import it.objectmethod.loobia.entity.CustomerAddresses;
import it.objectmethod.loobia.repository.CustomerAddressesRepository;

@SuppressWarnings("unchecked")
public class CustomerAddressesInvoiceRule implements IValidatorRule {

	private Integer getBillingAddress(List<CustomerAddresses> addresses) {
		Integer invId = null;
		for (CustomerAddresses c : addresses) {
			if (c.getFatturazione()) {
				invId = c.getId();
			}
		}
		return invId;
	}

	@Override
	public void validate(Map<String, Object> paramsToValidate) {

		List<String> errors = (List<String>) paramsToValidate.get("errList");
		CustomerAddressesDto customAddressInsertDto = (CustomerAddressesDto) paramsToValidate.get("customerAddressDto");
		CustomerAddressesRepository customerAddressesRepo = (CustomerAddressesRepository) paramsToValidate
				.get("customerAddressesRepo");
		List<CustomerAddresses> addresses = null;

		if (customAddressInsertDto != null && customerAddressesRepo != null) {
			addresses = customerAddressesRepo.findCustomerAddresses(customAddressInsertDto.getIdCliente());
			if (addresses == null || addresses.isEmpty()) {
				customAddressInsertDto.setFatturazione(true);
			} else {
				Integer billingAddressId = getBillingAddress(addresses);

				if (customAddressInsertDto.getFatturazione()) {
					if (billingAddressId != null) {
						CustomerAddresses billingAddr = customerAddressesRepo.findById(billingAddressId).get();
						billingAddr.setFatturazione(false);
						customerAddressesRepo.save(billingAddr);
						// customerAddressesRepo.updateCustomerAddressesById(billingAddressId);
					}
				} else if (customAddressInsertDto.getId() != null) {
					if (billingAddressId.equals(customAddressInsertDto.getId())) {
						errors.add("Non è possibile disabilitare questo indirizzo di fatturazione");
					}
				}
			}

		}

	}

}
