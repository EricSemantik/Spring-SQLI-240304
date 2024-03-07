package spring.formation.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.formation.model.Utilisateur;

public interface IUtilisateurRepository extends JpaRepository<Utilisateur, Long> {
	Optional<Utilisateur> findByUsername(String username);

	Optional<Utilisateur> findByUsernameAndPassword(String username, String password);
}
