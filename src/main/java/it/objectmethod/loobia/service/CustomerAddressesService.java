package it.objectmethod.loobia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.objectmethod.loobia.entity.Customer;
import it.objectmethod.loobia.entity.CustomerAddresses;
import it.objectmethod.loobia.repository.CustomerAddressesRepository;
import it.objectmethod.loobia.repository.CustomerRepository;

@Component
public class CustomerAddressesService {

	@Autowired
	private CustomerAddressesRepository custAddrRepo;

	@Autowired
	private CustomerRepository customerRepo;

	public CustomerAddresses findCustomerAddressById(Integer id) {
		CustomerAddresses customerAddress = custAddrRepo.findCustomerAddressById(id);
		return customerAddress;
	}

	public CustomerAddresses customerAddressSave(CustomerAddresses customerAddress) {

		Customer customer = customerRepo.findCustomerById(customerAddress.getId());
		CustomerAddresses customerAddressSaved = null;
		if (customerAddress != null && customer != null) {
			customerAddress.setCustomer(customer);
			customerAddressSaved = custAddrRepo.save(customerAddress);

		}

		return customerAddressSaved;
	}
}
