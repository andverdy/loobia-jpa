package it.objectmethod.loobia.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import it.objectmethod.loobia.entity.Order;

@Transactional
@Component
public interface OrderRepository extends JpaRepository<Order, Integer> {

	List<Order> findByEsportato(@Param("esportato") String esportato);

}
