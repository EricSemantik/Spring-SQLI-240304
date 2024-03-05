package spring.formation.repo.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import spring.formation.model.Client;
import spring.formation.repo.IClientRepository;

@Repository
@Transactional(readOnly = true)
public class ClientRepositoryJpa implements IClientRepository {

	@PersistenceContext
	private EntityManager em;

	public ClientRepositoryJpa() {
	}

	public List<Client> findAll() {
		return em.createQuery("select c from Client c", Client.class).getResultList();
	}

	@Override
	public Optional<Client> findById(Long id) {
		return Optional.of(em.createQuery("select c from Client c where c.id = ?1",
				Client.class).setParameter(1, id).getSingleResult());
	}

	@Transactional(readOnly = false)
	public Client save(Client entity) {
		if (!em.contains(entity)) {
			em.persist(entity);
		}

		else { // UPDATE
			entity = em.merge(entity);
		}

		return entity;
	}

	@Transactional(readOnly = false)
	public void deleteById(Long id) {
		em.createQuery("delete from Client c where c.id = ?1").setParameter(1, id).executeUpdate();
	}

}
