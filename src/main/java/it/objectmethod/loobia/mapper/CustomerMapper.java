package it.objectmethod.loobia.mapper;

import org.mapstruct.Mapper;

import it.objectmethod.loobia.dto.CustomerDto;
import it.objectmethod.loobia.entity.Customer;
@Mapper(componentModel = "spring")
public interface CustomerMapper extends EntityMapper<CustomerDto, Customer> {

	CustomerDto toDto(Customer Custmoer);

}
