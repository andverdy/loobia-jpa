package it.objectmethod.loobia.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

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

		if (email != null && !(custRepo.existsById(customer.getId()))) {
			String codzona = userRepo.findCodzonaByEmail(email);

			String newClientCode = cliendCode();

			customer.setCodiceZona(codzona);
			customer.setCodiceCliente(newClientCode);
		}
		Customer customerUpdate = new Customer();
		customerUpdate = custRepo.findById(customer.getId()).get();
		customer.setCodiceZona(customerUpdate.getCodiceZona());
		customer.setCodiceCliente(customerUpdate.getCodiceCliente());
		custRepo.save(customer);
		return customerMapper.toDto(customer);
	}

	private String cliendCode() {
		List<Customer> customers = this.custRepo.findAll();

		List<String> codeClients = new ArrayList<>();
		customers.forEach(customer -> {
			if (customer.getCodiceCliente() != null) {
				codeClients.add(customer.getCodiceCliente());
			}
		});

		String lastCode = null;
		if (codeClients.size() > 0) {
			lastCode = codeClients.get(codeClients.size() - 1);
		}

		String codiceClienteAlfab = "CL";
		Integer codiceClienteNum = Integer.parseInt(lastCode.substring(2));

		DecimalFormat decimalFormat = new DecimalFormat("0000");

		System.out.println(decimalFormat.format(codiceClienteNum + 1));
		String newClientCode = codiceClienteAlfab + decimalFormat.format(codiceClienteNum + 1);

		return newClientCode;

	}

}
