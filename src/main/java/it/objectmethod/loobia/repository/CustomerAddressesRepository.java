package it.objectmethod.loobia.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.objectmethod.loobia.entity.CustomerAddresses;

@Transactional
@Repository
public interface CustomerAddressesRepository extends JpaRepository<CustomerAddresses, Integer> {

	CustomerAddresses findCustomerAddressesById(@Param("id") Integer id);

	@Query(value = "select * from indirizzi_cliente where fatturazione = :fatturazione AND id = :id", nativeQuery = true)
	CustomerAddresses findCustomerAddressesByIdAndFatturazione(@Param("id") Integer id,
			@Param("fatturazione") boolean fatturazione);

	@Modifying
	@Query(value = "UPDATE indirizzi_cliente SET fatturazione = FALSE WHERE id = :id", nativeQuery = true)
	Integer updateCustomerAddressesById(@Param("id") Integer id);

	@Query(value = "select max(id) as max from indirizzi_cliente;", nativeQuery = true)
	Integer searchMaxId();

	@Query(value = "select * from indirizzi_cliente where id_cliente = :cliente", nativeQuery = true)
	List<CustomerAddresses> findCustomerAddresses(@Param("cliente") Integer id);

	@Modifying
	@Query(value = "delete from indirizzi_cliente where id = :id", nativeQuery = true)
	void deleteCustomAddress(@Param("id") Integer id);

}
