package it.objectmethod.loobia.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import it.objectmethod.loobia.dto.OrderDetailDto;

import it.objectmethod.loobia.entity.OrderDetails;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper extends EntityMapper<OrderDetailDto, OrderDetails> {

	List<OrderDetails> toEntity(List<OrderDetailDto> dtoList);

}
