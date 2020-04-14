package it.objectmethod.loobia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import it.objectmethod.loobia.dto.CustomerAddressesDto;
import it.objectmethod.loobia.entity.Customer;
import it.objectmethod.loobia.entity.CustomerAddresses;
import it.objectmethod.loobia.mapper.CustomerAddressesMapper;
import it.objectmethod.loobia.repository.CustomerAddressesRepository;

@Component
public class CustomerAddressesService {

	@Autowired
	private CustomerAddressesRepository custAddrRepo;

	@Autowired
	private CustomerAddressesMapper custAddrMapp;

	public CustomerAddresses findCustomerAddressById(Integer id) {
		CustomerAddresses customerAddress = custAddrRepo.findCustomerAddressesById(id);
		return customerAddress;
	}

	public CustomerAddressesDto customerAddressSave(CustomerAddressesDto customerAddressDto) {

		CustomerAddresses customerAddress = new CustomerAddresses();
		Customer customer = new Customer();

		customer.setId(customerAddressDto.getIdCliente());

		customerAddress.setId(customerAddressDto.getId());
		customerAddress.setCustomer(customer);
		customerAddress.setCitta(customerAddressDto.getCitta());
		customerAddress.setCap(customerAddressDto.getCap());
		customerAddress.setNazione(customerAddressDto.getNazione());
		customerAddress.setIndirizzo(customerAddressDto.getIndirizzo());
		customerAddress.setProvincia(customerAddressDto.getProvincia());
		customerAddress.setDestinatario(customerAddressDto.getDestinatario());
		customerAddress.setAttivo(customerAddressDto.getAttivo());
		customerAddress.setFatturazione(customerAddressDto.getFatturazione());

		CustomerAddresses customerAddressSaved = custAddrRepo.save(customerAddress);
		return custAddrMapp.toDto(customerAddressSaved);
	}

	public void deleteCustomerAddress(Integer id) {
		custAddrRepo.deleteCustomAddress(id);
	}
}
