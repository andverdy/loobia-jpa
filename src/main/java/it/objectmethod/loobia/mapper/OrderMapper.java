package it.objectmethod.loobia.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.objectmethod.loobia.dto.OrderDto;
import it.objectmethod.loobia.entity.Order;

@Mapper(componentModel = "spring", uses = { OrderDetailMapper.class, CustomerMapper.class,
		PaymentConditionsMapper.class })
public interface OrderMapper extends EntityMapper<OrderDto, Order> {

	@Mapping(source = "detailOrders", target = "detailOrdersDto")
	@Mapping(source = "customerOrder.id", target = "idCliente")
	@Mapping(source = "paymentConditions.id", target = "idCondizioniPagamento")
	OrderDto toDto(Order entity);
}
