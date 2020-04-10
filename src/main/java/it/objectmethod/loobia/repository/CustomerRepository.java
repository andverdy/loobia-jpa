package it.objectmethod.loobia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import it.objectmethod.loobia.entity.Customer;

@Component
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query(value = "select codice_cliente from cliente ORDER BY codice_cliente DESC LIMIT 1;", nativeQuery = true)
	String findLastCustomerCodeSortedAlphabetically();

	Customer findCustomerById(@Param("id") Integer id);

	@Query(value = "select ragione_sociale from cliente where id = :id", nativeQuery = true)
	String findRagioneSocialeById(@Param("id") Integer id);

	@Query(value = "select id from cliente where codice_zona = :codZona", nativeQuery = true)
	List<Integer> findAllCustomerIdByCodZona(@Param("codZona") String codZona);
}
