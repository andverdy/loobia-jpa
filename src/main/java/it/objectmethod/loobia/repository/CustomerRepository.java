package it.objectmethod.loobia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.objectmethod.loobia.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query(value = "select codice_cliente from cliente ORDER BY codice_cliente DESC LIMIT 1;", nativeQuery = true)
	String findLastCustomerCodeSortedAlphabetically();
}