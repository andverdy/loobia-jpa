package it.objectmethod.loobia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import it.objectmethod.loobia.entity.CommissionCopy;

@Component
public interface CommissionCopyRepository extends JpaRepository<CommissionCopy, Integer> {

}
