package spring.formation.repo.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spring.formation.config.ApplicationConfig;
import spring.formation.model.Fournisseur;
import spring.formation.repo.IFournisseurRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FournisseurRepositoryJpaTest {
	@Autowired
	private IFournisseurRepository repoFournisseur;

	@Test
	public void testFindAll() {
		List<Fournisseur> fournisseurs = repoFournisseur.findAll();

		assertNotNull(fournisseurs);
		assertNotEquals(0, fournisseurs.size());
		assertNotEquals(Long.valueOf(0), fournisseurs.get(0).getId());
		assertNotNull(fournisseurs.get(0).getNom());
	}

//	@Test
	public void testFindById() {
		Long fournisseurId = 1L;
		Fournisseur fournisseur = repoFournisseur.findById(fournisseurId).get();

		assertNotNull(fournisseur);
		assertNotNull(fournisseur.getProduits());
		assertNotEquals(0, fournisseur.getProduits().size());
	}

//	@Test
	public void shouldAdd() {
		Fournisseur fournisseur = new Fournisseur();

		fournisseur.setNom("F1");
		fournisseur.setResponsable("RESP");

		fournisseur = repoFournisseur.save(fournisseur);

		assertNotEquals(Long.valueOf(0), fournisseur.getId());
	}

//	@Test
	public void shouldUpdate() {
		Long fournisseurId = this.getLastId();
		String fournisseurNom = UUID.randomUUID().toString();
		Fournisseur fournisseur = repoFournisseur.findById(fournisseurId).get();

		fournisseur.setNom(fournisseurNom);
		repoFournisseur.save(fournisseur);

		fournisseur = repoFournisseur.findById(fournisseurId).get();

		assertNotNull(fournisseur);
		assertEquals(fournisseurId, fournisseur.getId());
		assertEquals(fournisseurNom, fournisseur.getNom());
	}

//	@Test
	public void testDeleteById() {
		Long fournisseurId = this.getLastId();
		repoFournisseur.deleteById(fournisseurId);

		Optional<Fournisseur> optFournisseur = repoFournisseur.findById(fournisseurId);

		assertNotNull(optFournisseur);
		assertFalse(optFournisseur.isPresent());
	}

	private Long getLastId() {
		List<Fournisseur> fournisseurs = repoFournisseur.findAll();
		return fournisseurs.get(fournisseurs.size() - 1).getId();
	}
}
