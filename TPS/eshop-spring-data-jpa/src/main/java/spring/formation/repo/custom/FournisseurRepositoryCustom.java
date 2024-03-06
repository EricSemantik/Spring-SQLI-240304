package spring.formation.repo.custom;

import java.util.List;
import java.util.Set;

import spring.formation.model.Fournisseur;

public interface FournisseurRepositoryCustom {
	List<Fournisseur> findAllFournisseurByEmails(Set<String> emails);
}
