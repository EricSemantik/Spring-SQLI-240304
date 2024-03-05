package spring.formation.repo.jpa;

import org.junit.Test;

import spring.formation.EshopApplication;
import spring.formation.model.Fournisseur;
import spring.formation.model.Produit;
import spring.formation.repo.IFournisseurRepository;
import spring.formation.repo.IProduitRepository;

public class PopulateTest {
	private IFournisseurRepository repoFournisseur = EshopApplication.getInstance().getFournisseurRepo();
	private IProduitRepository repoProduit = EshopApplication.getInstance().getProduitRepo();

	@Test
	public void populate() {
		Fournisseur fournisseur = new Fournisseur();
		fournisseur.setNom("F1");
		fournisseur.setResponsable("RESP");

		fournisseur = this.repoFournisseur.save(fournisseur);

		
		Produit produit = new Produit("NEW");
		produit.setPrixAchat(10d);
		produit.setPrixVente(100d);
		produit.setFournisseur(fournisseur);
		produit.setModele("MOD");
		produit.setReference("REF");

		produit = this.repoProduit.save(produit);
	}
}
