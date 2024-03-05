package spring.formation.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;

@Entity
@DiscriminatorValue("client")
@NamedQuery(name = "Client.findByVille", query="select c from Client c join c.adresses adr where adr.ville = :ville")
public class Client extends Personne {
	@Column(name = "CLI_PRENOM", length = 100)
	private String prenom;

	@OneToMany(mappedBy = "client")
	private List<Commande> commandes = new ArrayList<>();

	@OneToMany(mappedBy = "client")
	private List<Adresse> adresses;

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public List<Commande> getCommandes() {
		return commandes;
	}

	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}

	public List<Adresse> getAdresses() {
		return adresses;
	}

	public void setAdresses(List<Adresse> adresses) {
		this.adresses = adresses;
	}
}
