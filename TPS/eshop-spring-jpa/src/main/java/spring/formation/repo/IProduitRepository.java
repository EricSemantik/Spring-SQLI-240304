package spring.formation.repo;

import java.util.List;

import spring.formation.model.Produit;

public interface IProduitRepository extends IRepository<Produit, Long> {
	public List<Produit> findByPrixBetween(Double a, Double b);
	
	public List<Produit> findByFournisseur(String nom);
}
