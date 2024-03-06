package spring.formation.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.formation.model.Fournisseur;
import spring.formation.repo.custom.FournisseurRepositoryCustom;

public interface IFournisseurRepository extends JpaRepository<Fournisseur, Long>, FournisseurRepositoryCustom {

}
