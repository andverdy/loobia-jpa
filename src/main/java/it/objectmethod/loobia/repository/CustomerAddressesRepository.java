package it.objectmethod.loobia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import it.objectmethod.loobia.entity.CustomerAddresses;

@Component
public interface CustomerAddressesRepository extends JpaRepository<CustomerAddresses, Integer>{

	CustomerAddresses findCustomerAddressById(@Param("id") Integer id);
}
