package spring.formation.repo.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import spring.formation.model.Produit;
import spring.formation.repo.IProduitRepository;

@Repository
@Transactional(readOnly = true)
public class ProduitRepositoryJpa implements IProduitRepository {
	
	@PersistenceContext
	private EntityManager em;

	public ProduitRepositoryJpa() {
	}

	public List<Produit> findAll() {
		return em.createQuery("select p from Produit p", Produit.class).getResultList();
	}

	public Optional<Produit> findById(Long id) {
		return Optional.of(em.createQuery("select p from Produit p where p.id = ?1", Produit.class).setParameter(1, id)
				.getSingleResult());
	}

	@Transactional(readOnly = false)
	public Produit save(Produit entity) {
		return em.merge(entity);
	}

	@Transactional(readOnly = false)
	public void deleteById(Long id) {
		em.createQuery("delete from Produit e where e.id = ?1").setParameter(1, id).executeUpdate();
	}

	@Override
	public List<Produit> findByPrixBetween(Double a, Double b) {
		return em.createQuery("select p from Produit p where p.prixVente between ?1 and ?2", Produit.class)
				.setParameter(1, a).setParameter(2, b).getResultList();
	}
}