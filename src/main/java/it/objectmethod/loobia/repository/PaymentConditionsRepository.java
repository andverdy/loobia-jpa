package it.objectmethod.loobia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import it.objectmethod.loobia.entity.PaymentConditions;

public interface PaymentConditionsRepository extends JpaRepository<PaymentConditions, Long> {

	PaymentConditions findSpesaById(@Param("id") Long id);
}
