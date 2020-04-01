package it.objectmethod.loobia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import it.objectmethod.loobia.entity.Municipality;

public interface MunicipalityRepository extends JpaRepository<Municipality, Integer> {

	List<Municipality> findByNomeStartingWith(@Param("nome") String nome);

}
