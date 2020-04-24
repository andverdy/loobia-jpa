package it.objectmethod.loobia.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import it.objectmethod.loobia.dto.OrderDetailsDto;

import it.objectmethod.loobia.entity.OrderDetails;

@Mapper(componentModel = "spring")
public interface OrderDetailsMapper extends EntityMapper<OrderDetailsDto, OrderDetails> {

	List<OrderDetails> toEntity(List<OrderDetailsDto> dtoList);

}
