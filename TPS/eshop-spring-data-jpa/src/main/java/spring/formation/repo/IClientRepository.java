package spring.formation.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import spring.formation.model.Client;

public interface IClientRepository extends JpaRepository<Client, Long> {
	// Par convention de nommage
	List<Client> findByNom(String nom);
	
	List<Client> findByNomStartingWith(String nom);
	
	// Par @NamedQuery
	List<Client> findByVille(@Param("ville") String ville);
	
	// Par @Query
	@Query("select c from Client c join c.commandes com where com.date = ?1")
	List<Client> findByDateCommande(Date date);
}
