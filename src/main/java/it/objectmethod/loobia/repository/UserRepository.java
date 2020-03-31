package it.objectmethod.loobia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import it.objectmethod.loobia.entity.User;


@Component
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

	String findByEmail(@Param("email") String email);
}
