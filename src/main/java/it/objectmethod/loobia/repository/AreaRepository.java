package it.objectmethod.loobia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import it.objectmethod.loobia.entity.Area;

@Component
public interface AreaRepository extends JpaRepository<Area, Integer> {

	Area findByCodZona(String codZona);

}
