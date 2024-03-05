package spring.formation.repo.jpa;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.formation.config.ApplicationConfig;
import spring.formation.model.Fournisseur;
import spring.formation.model.Produit;
import spring.formation.repo.IFournisseurRepository;
import spring.formation.repo.IProduitRepository;

public class PopulateTest {
	private static AnnotationConfigApplicationContext context;
	private static IFournisseurRepository repoFournisseur;
	private static IProduitRepository repoProduit;

	@BeforeClass
	public static void start() {
		context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		repoFournisseur = context.getBean(IFournisseurRepository.class);
		repoProduit = context.getBean(IProduitRepository.class);
	}

	@AfterClass
	public static void stop() {
		context.close();
	}

	

	@Test
	public void populate() {
		Fournisseur fournisseur = new Fournisseur();
		fournisseur.setNom("F1");
		fournisseur.setResponsable("RESP");

		fournisseur = repoFournisseur.save(fournisseur);

		Produit produit = new Produit("NEW");
		produit.setPrixAchat(10d);
		produit.setPrixVente(100d);
		produit.setFournisseur(fournisseur);
		produit.setModele("MOD");
		produit.setReference("REF");

		produit = repoProduit.save(produit);
	}
}
