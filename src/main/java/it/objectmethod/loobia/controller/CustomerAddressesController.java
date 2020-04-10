package it.objectmethod.loobia.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import it.objectmethod.loobia.dto.CustomerAddressesDto;
import it.objectmethod.loobia.entity.CustomerAddresses;
import it.objectmethod.loobia.repository.AreaRepository;
import it.objectmethod.loobia.repository.CustomerAddressesRepository;
import it.objectmethod.loobia.repository.CustomerRepository;
import it.objectmethod.loobia.repository.MunicipalityRepository;
import it.objectmethod.loobia.repository.UserRepository;
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

	@Autowired
	private CustomerAddressesRepository customerAddressesRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private AreaRepository areRepo;

	@GetMapping("/by_id")
	public CustomerAddresses findCustomerAddressById(@RequestParam(value = "id") Integer id) {
		return custAddrService.findCustomerAddressById(id);
	}

	@PutMapping("/save")
	public CustomerAddressesDto customerAddressSave(HttpServletRequest request,
			@RequestBody CustomerAddressesDto customerAddressDto) {
		String email = (String) request.getAttribute("email");

		List<String> errors = customerAddressesValidator.validateCustomerAddress(areRepo, customerRepo, userRepo, email,
				customerAddressDto, mucipRepo, customerAddressesRepo);
		if ((!errors.isEmpty())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.toString());
		}
		return custAddrService.customerAddressSave(customerAddressDto);
	}

	@DeleteMapping("/delete")
	public void deleteCustomerAddress(@RequestParam("id") Integer id) {
		custAddrService.deleteCustomerAddress(id);
	}

}
