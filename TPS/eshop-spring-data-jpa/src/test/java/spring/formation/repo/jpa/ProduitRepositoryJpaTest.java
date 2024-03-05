package spring.formation.repo.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spring.formation.config.ApplicationConfig;
import spring.formation.model.Fournisseur;
import spring.formation.model.Produit;
import spring.formation.repo.IProduitRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class ProduitRepositoryJpaTest {
	@Autowired
	private IProduitRepository repoProduit;

	@Test
	public void testFindAll() {
		List<Produit> produits = repoProduit.findAll();

		assertNotNull(produits);
		assertNotEquals(0, produits.size());
		assertNotEquals(Long.valueOf(0), produits.get(0).getId());
		assertNotNull(produits.get(0).getLibelle());
	}

//	@Test
	public void testFindById() {
		Long produitId = 1L;
		Produit produit = repoProduit.findById(produitId).get();

		assertNotNull(produit);
		assertNotNull(produit.getDetails());
	}

//	@Test
	public void shouldAdd() {
		Produit produit = new Produit("NEW");

		produit.setPrixAchat(10d);
		produit.setPrixVente(100d);
		produit.setFournisseur(new Fournisseur());
		produit.getFournisseur().setId(1L);
		produit.setModele("MOD");
		produit.setReference("REF");

		produit = repoProduit.save(produit);

		assertNotEquals(Long.valueOf(0), produit.getId());
	}

//	@Test
	public void shouldUpdate() {
		Long produitId = this.getLastId();
		String produitNom = UUID.randomUUID().toString();
		Produit produit = repoProduit.findById(produitId).get();

		produit.setLibelle(produitNom);
		repoProduit.save(produit);

		produit = repoProduit.findById(produitId).get();

		assertNotNull(produit);
		assertEquals(produitId, produit.getId());
		assertEquals(produitNom, produit.getLibelle());
	}

//	@Test
	public void testDeleteById() {
		Long produitId = this.getLastId();
		repoProduit.deleteById(produitId);

		Optional<Produit> optProduit = repoProduit.findById(produitId);

		assertNotNull(optProduit);
		assertFalse(optProduit.isPresent());
	}

	private Long getLastId() {
		List<Produit> fournisseurs = repoProduit.findAll();
		return fournisseurs.get(fournisseurs.size() - 1).getId();
	}
}
