package it.objectmethod.loobia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import it.objectmethod.loobia.entity.Area;

@Component
public interface AreaRepository extends JpaRepository<Area, Integer> {

	Area findByCodZona(String codZona);

	@Query(value = "select z.codice_zona from zona as z  where z.id_agente = :idUtente", nativeQuery = true)
	List<String> findAllCodZonaById(@Param("idUtente") Integer idUtente);

	
	@Query(value = "select * from zona where id = :idAgente", nativeQuery = true)
	Area findById(@Param("idAgente") Long IdAgente);
}
