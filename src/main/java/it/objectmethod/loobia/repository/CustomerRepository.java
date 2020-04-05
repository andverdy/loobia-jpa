package it.objectmethod.loobia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import it.objectmethod.loobia.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

//	@Query("SELECT u.idutente FROM User u WHERE u.email = ?1")
//	Integer findIdutenteByEmail(String email);
//	
//	@Query("SELECT a.codzona FROM Area a WHERE a.id_agente = ?1")
//	String findCodzonaById_agente(Integer integer);

	/*
	 * @Query("select u.userName from User u inner join **u.area** ar where
	 * ar.idArea = :idArea")
	 */

	/*
	 * @Query("SELECT id FROM Student std JOIN Offer off ON off.id = std.offerId")
	 */

	/*
	 * @Query(value="select c from City c where c.codiceNazione = 'ITA'") public
	 * List<City> findItalianCities();
	 */
	
	//Customer findByEmail(@Param("email") String email);
}
