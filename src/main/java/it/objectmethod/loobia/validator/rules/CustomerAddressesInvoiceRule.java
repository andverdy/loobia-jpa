package it.objectmethod.loobia.validator.rules;

import java.util.List;
import java.util.Map;

import it.objectmethod.loobia.dto.CustomerAddressesDto;
import it.objectmethod.loobia.entity.CustomerAddresses;
import it.objectmethod.loobia.repository.CustomerAddressesRepository;
import it.objectmethod.loobia.repository.CustomerRepository;

@SuppressWarnings("unchecked")
public class CustomerAddressesInvoiceRule implements IValidatorRule {

	private Integer getAddressesInvoice(List<CustomerAddresses> addresses) {
		for (CustomerAddresses c : addresses) {
			if (c.getFatturazione()) {
				return c.getId();
			}
		}
		return null;
	}

	@Override
	public void validate(Map<String, Object> paramsToValidate) {

		List<String> errors = (List<String>) paramsToValidate.get("errList");
		CustomerAddressesDto customAddressInsertDto = (CustomerAddressesDto) paramsToValidate.get("customerAddressDto");
		CustomerAddressesRepository customerAddressesRepo = (CustomerAddressesRepository) paramsToValidate
				.get("customerAddressesRepo");
		CustomerRepository customerRepo = (CustomerRepository) paramsToValidate.get("custRepo");
		List<CustomerAddresses> addresses = null;

		if (customAddressInsertDto != null && customerAddressesRepo != null) {
			if (customAddressInsertDto.getDestinatario() == null
					|| customAddressInsertDto.getDestinatario().equals("")) {

				String customerExtractSocialReason = customerRepo
						.findRagioneSocialeById(customAddressInsertDto.getIdCliente());
				customAddressInsertDto.setDestinatario(customerExtractSocialReason);
			}
			addresses = customerAddressesRepo.findCustomerAddresses(customAddressInsertDto.getIdCliente());
			if (addresses != null && addresses.isEmpty()) {
				customAddressInsertDto.setFatturazione(true);
			} else if (addresses != null && addresses.size() > 0) {

				if (customAddressInsertDto.getFatturazione()) {
					Integer invoiceAddress = getAddressesInvoice(addresses);
					if (invoiceAddress != null) {
						customerAddressesRepo.updateCustomerAddressesById(invoiceAddress);
					}
				} else if ((customAddressInsertDto.getId() != null && !customAddressInsertDto.getFatturazione())) {
					Integer addressInvoice = getAddressesInvoice(addresses);
					if (addressInvoice.equals(customAddressInsertDto.getId())) {
						errors.add("Non è possibile disabilitare questo indirizzo di fatturazione");
					}
				}
			}

		}

	}

}
