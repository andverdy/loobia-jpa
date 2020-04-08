package it.objectmethod.loobia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import it.objectmethod.loobia.entity.CustomerAddresses;
import it.objectmethod.loobia.repository.MunicipalityRepository;
import it.objectmethod.loobia.service.CustomerAddressesService;
import it.objectmethod.loobia.validators.CustomerAddressesValidator;

@RestController
@CrossOrigin
@RequestMapping("/api/customer_addresses")
public class CustomerAddressesController {

	@Autowired
	private CustomerAddressesService custAddrService;

	@Autowired
	private MunicipalityRepository mucipRepo;

	@Autowired
	private CustomerAddressesValidator customerAddressesValidator;

	@GetMapping("/by_id")
	public CustomerAddresses findCustomerAddressById(@RequestParam(value = "id") Integer id) {
		return custAddrService.findCustomerAddressById(id);
	}

	@PutMapping("/save")
	public CustomerAddresses customerAddressSave(@RequestBody CustomerAddresses customerAddress) {
		List<String> errors = customerAddressesValidator.validateCustomerAddress(customerAddress, mucipRepo);
		if ((!errors.isEmpty())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.toString());
		}
		return custAddrService.customerAddressSave(customerAddress);
	}
}
