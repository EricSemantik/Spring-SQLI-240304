package spring.formation.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import spring.formation.model.Fournisseur;
import spring.formation.repo.custom.FournisseurRepositoryCustom;

public interface IFournisseurRepository extends JpaRepository<Fournisseur, Long>, FournisseurRepositoryCustom {
	@Query("select distinct f from Fournisseur f left join fetch f.adresses")
	List<Fournisseur> findAllWithAdresses();
}
