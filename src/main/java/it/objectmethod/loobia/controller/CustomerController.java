package it.objectmethod.loobia.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import it.objectmethod.loobia.dto.CustomerDto;
import it.objectmethod.loobia.entity.Customer;
import it.objectmethod.loobia.repository.MunicipalityRepository;
import it.objectmethod.loobia.service.CustomerService;
import it.objectmethod.loobia.validators.CustomerValidator;

@RestController
@CrossOrigin
@RequestMapping("/api/customer")
public class CustomerController {

	@Autowired
	private CustomerService custService;

	@Autowired
	private CustomerValidator customerValidator;

	@Autowired
	private MunicipalityRepository municRepo;

	@PutMapping("/save")
	public CustomerDto customerSave(HttpServletRequest request, @RequestBody Customer customer) {
		String email = (String) request.getAttribute("email");
		List<String> errors = customerValidator.validateCustomer(customer, municRepo);
		if ((!errors.isEmpty())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.toString());
		}
		return custService.customerSave(customer, email);

	}

}
