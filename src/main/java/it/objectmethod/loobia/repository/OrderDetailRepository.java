package it.objectmethod.loobia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import it.objectmethod.loobia.entity.OrderDetails;

@Component
public interface OrderDetailRepository extends JpaRepository<OrderDetails, Integer> {

	@Query(value = " select id_prodotto from copia_commissione_dettaglio where id = :id", nativeQuery = true)
	Long findIdProdottoById(@Param("id") Long id);
}
