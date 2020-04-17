package it.objectmethod.loobia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.objectmethod.loobia.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

			@Query(value = "select prezzo from prodotto where id = :id", nativeQuery = true)
			Float findPrezzoById(@Param("id") Integer id);
}
