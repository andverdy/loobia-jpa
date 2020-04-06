package it.objectmethod.loobia.service;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.objectmethod.loobia.dto.CustomerDto;
import it.objectmethod.loobia.entity.Customer;
import it.objectmethod.loobia.mapper.CustomerMapper;
import it.objectmethod.loobia.repository.CustomerRepository;
import it.objectmethod.loobia.repository.UserRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository custRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private CustomerMapper customerMapper;

	public CustomerDto customerSave(Customer customer, String email) {

		if (email != null && customer.getId() == null) {
			String codzona = userRepo.findCodZonaByEmail(email);
			String newClientCode = generateCustmerCode();
			customer.setCodZona(codzona);
			customer.setCodiceCliente(newClientCode);
		}
		customer = custRepo.save(customer);
		return customerMapper.toDto(customer);
	}

	private String generateCustmerCode() {

		String lastCustomerCode = custRepo.findLastCustomerCodeSortedAlphabetically();
		String customerCodeAlfab = "CL";
		Integer customerCodeNum = Integer.parseInt(lastCustomerCode.substring(2));
		DecimalFormat decimalFormat = new DecimalFormat("0000");
		String newCustomerCode = customerCodeAlfab + decimalFormat.format(customerCodeNum + 1);
		return newCustomerCode;

	}

}
