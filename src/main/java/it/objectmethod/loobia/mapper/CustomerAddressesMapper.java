package it.objectmethod.loobia.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import it.objectmethod.loobia.dto.CustomerAddressesDto;
import it.objectmethod.loobia.entity.CustomerAddresses;

@Mapper(componentModel = "spring", uses = { CustomerMapper.class })
public interface CustomerAddressesMapper extends EntityMapper<CustomerAddressesDto, CustomerAddresses> {

	@Mapping(source = "customer.id", target = "idCliente")
	CustomerAddressesDto toDto(CustomerAddresses entity);
}
