package spring.formation.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import spring.formation.model.Produit;

public interface IProduitRepository extends JpaRepository<Produit, Long> {
	public List<Produit> findByPrixVenteBetween(Double a, Double b); // par convention de nommage
	
	public List<Produit> findByFournisseur(String nom); // par NamedQuery Produit.findByFournisseur

	@Query("select p from Produit p order by p.prixVente desc")
	public List<Produit> findAllOrderByPrixVenteDesc();
}
