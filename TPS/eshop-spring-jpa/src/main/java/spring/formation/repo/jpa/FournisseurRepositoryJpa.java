package spring.formation.repo.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import spring.formation.model.Fournisseur;
import spring.formation.repo.IFournisseurRepository;

@Component
public class FournisseurRepositoryJpa implements IFournisseurRepository {
	
	@Autowired
	private EntityManagerFactory emf;
	
	public FournisseurRepositoryJpa() {
	}

	public List<Fournisseur> findAll() {
		EntityManager em = emf.createEntityManager();

		try {
			return em.createQuery("select f from Fournisseur f", Fournisseur.class).getResultList();
		}

		catch (Exception ex) {
			ex.printStackTrace();
			return new ArrayList<>();
		}

		finally {
			em.close();
		}
	}

	@Override
	public Optional<Fournisseur> findById(Long id) {
		EntityManager em = emf.createEntityManager();

		try {
			return Optional
					.of(em.createQuery("select f from Fournisseur f left join fetch f.produits p where f.id = ?1",
							Fournisseur.class).setParameter(1, id).getSingleResult());
		}

		catch (Exception ex) {
			return Optional.empty();
		}

		finally {
			em.close();
		}
	}

	public Fournisseur save(Fournisseur entity) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		try {
			if (!em.contains(entity)) {
				em.persist(entity);
			}

			else { // UPDATE
				entity = em.merge(entity);
			}

			em.getTransaction().commit();
		}

		catch (Exception ex) {
			ex.printStackTrace();
			em.getTransaction().rollback();
		}

		finally {
			em.close();
		}

		return entity;
	}

	public void deleteById(Long id) {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		try {
			em.createQuery("delete from Fournisseur e where e.id = ?1").setParameter(1, id).executeUpdate();
			em.getTransaction().commit();
		}

		catch (Exception ex) {
			ex.printStackTrace();
			em.getTransaction().rollback();
		}

		finally {
			em.close();
		}
	}

}
