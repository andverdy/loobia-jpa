package it.objectmethod.loobia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import it.objectmethod.loobia.entity.CommissionDetailCopy;

@Component
public interface CommissionCopyDetailRepository extends JpaRepository<CommissionDetailCopy, Integer> {

}
