package it.objectmethod.loobia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import it.objectmethod.loobia.dto.OrderDto;
import it.objectmethod.loobia.entity.Order;
import it.objectmethod.loobia.mapper.OrderMapperCustomized;
import it.objectmethod.loobia.repository.OrderRepository;
import it.objectmethod.loobia.validators.OrderDetailValidator;
import it.objectmethod.loobia.validators.OrderValidator;

@Component
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderValidator orderValid;

	@Autowired
	private OrderDetailValidator orderDetailValid;

	@Autowired
	private OrderMapperCustomized orderMapperCustom;

	public OrderDto orderSave(OrderDto orderDto) {
		List<String> errorsOrder = orderValid.orderValidator(orderDto);
		if ((!errorsOrder.isEmpty())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorsOrder.toString());
		}
		List<String> errorsDetail = orderDetailValid.orderDetailValidator(orderDto);
		if ((!errorsDetail.isEmpty())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorsDetail.toString());
		}
		
		Order newOrder = orderMapperCustom.toEntity(orderDto);
		orderRepository.save(newOrder);
		OrderDto orderDtoFinal = orderMapperCustom.toDto(newOrder);
		return orderDtoFinal;
	}
}
