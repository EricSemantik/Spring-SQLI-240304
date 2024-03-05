package spring.formation;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import spring.formation.repo.IFournisseurRepository;
import spring.formation.repo.IProduitRepository;
import spring.formation.repo.jpa.FournisseurRepositoryJpa;
import spring.formation.repo.jpa.ProduitRepositoryJpa;

public class EshopApplication {

	private static EshopApplication instance = null;

	private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("EshopUnit");
	private final IFournisseurRepository fournisseurRepo = new FournisseurRepositoryJpa();
	private final IProduitRepository produitRepo = new ProduitRepositoryJpa();

	private EshopApplication() {
	}

	public static EshopApplication getInstance() {
		if (instance == null) {
			instance = new EshopApplication();
		}

		return instance;
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public IFournisseurRepository getFournisseurRepo() {
		return fournisseurRepo;
	}

	public IProduitRepository getProduitRepo() {
		return produitRepo;
	}

}
