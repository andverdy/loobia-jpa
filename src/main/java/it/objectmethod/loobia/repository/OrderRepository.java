package it.objectmethod.loobia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import it.objectmethod.loobia.entity.Order;
@Component
public interface OrderRepository extends JpaRepository<Order, Integer>{

}
