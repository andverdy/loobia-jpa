package it.objectmethod.loobia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import it.objectmethod.loobia.entity.Municipality;

@Component
public interface MunicipalityRepository extends JpaRepository<Municipality, Integer> {

	@Query(value = "select * from comune WHERE nome LIKE 'ab%' limit 10", nativeQuery = true)
	List<Municipality> searchByNomeStartsWith(@Param("nome") String nome);

	Municipality findByNome(@Param("nome") String nome);

}
