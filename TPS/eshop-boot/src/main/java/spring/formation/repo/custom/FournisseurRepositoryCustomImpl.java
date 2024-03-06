package spring.formation.repo.custom;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import spring.formation.model.Fournisseur;

public class FournisseurRepositoryCustomImpl implements FournisseurRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Fournisseur> findAllFournisseurByEmails(Set<String> emails) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Fournisseur> query = cb.createQuery(Fournisseur.class); // Query<Fournisseur>
        Root<Fournisseur> fournisseur = query.from(Fournisseur.class); // from Fournisseur f

        Path<String> adressePath = fournisseur.get("adresse"); // f.adresse

        List<Predicate> predicates = new ArrayList<>();
        for (String email : emails) {
            predicates.add(cb.equal(adressePath, email)); // f.adresse = :email[0]
        }
        query.select(fournisseur)
            .where(cb.or(predicates.toArray(new Predicate[predicates.size()])));  // where f.adresse = :email[0] or f.adresse = :email[1]

        return entityManager.createQuery(query)
            .getResultList();
	}

}
