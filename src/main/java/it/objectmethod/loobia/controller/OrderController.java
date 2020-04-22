package it.objectmethod.loobia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import it.objectmethod.loobia.dto.OrderDto;
import it.objectmethod.loobia.service.OrderService;

@RestController
@CrossOrigin
@RequestMapping("/api/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PutMapping("/save")
	public OrderDto commissionCopySave(@RequestBody OrderDto orderdto) {

		return orderService.orderSave(orderdto);

	}
}
