package spring.formation.api.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import spring.formation.model.Fournisseur;

public class FournisseurResponse {
	private Long id;
	private String nom;
	private String adresse;
	private String responsable;
	private List<Long> idProduits = new ArrayList<>();
	private List<AdresseResponse> adresses = new ArrayList<>();

	public FournisseurResponse() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public List<Long> getIdProduits() {
		return idProduits;
	}

	public void setIdProduits(List<Long> idProduits) {
		this.idProduits = idProduits;
	}

	public List<AdresseResponse> getAdresses() {
		return adresses;
	}

	public void setAdresses(List<AdresseResponse> adresses) {
		this.adresses = adresses;
	}
	
	public static FournisseurResponse convert(Fournisseur fournisseur) {
		FournisseurResponse fournisseurResponse = new FournisseurResponse();
    	BeanUtils.copyProperties(fournisseur, fournisseurResponse);
    	
    	return fournisseurResponse;
	}

}
