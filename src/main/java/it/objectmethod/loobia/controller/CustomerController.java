package it.objectmethod.loobia.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.loobia.dto.CustomerDto;
import it.objectmethod.loobia.entity.Customer;
import it.objectmethod.loobia.service.CustomerService;

@RestController
@CrossOrigin
@RequestMapping("/api/customer")
public class CustomerController {

	@Autowired
	private CustomerService custService;

	@PutMapping("/save")
	public CustomerDto customerSave(HttpServletRequest request, @RequestBody Customer customer) {
		String email = (String) request.getAttribute("email");
		System.out.println(email);

		return custService.customerSave(customer, email);

	}

}
