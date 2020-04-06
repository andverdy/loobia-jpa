package it.objectmethod.loobia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import it.objectmethod.loobia.entity.User;

@Component
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

	@Query("select a.codZona from User u JOIN u.areas a where u.email = :email")
	String findCodZonaByEmail(@Param("email") String email);

	User findByEmail(@Param("email") String email);

}
