package spring.formation.api.response;

import org.springframework.beans.BeanUtils;

import spring.formation.model.Adresse;

public class AdresseResponse {
	private Long id;
	private String rue;
	private String ville;
	private String codePostal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public static AdresseResponse convert(Adresse adresse) {
		AdresseResponse adresseResponse = new AdresseResponse();
		BeanUtils.copyProperties(adresse, adresseResponse);
		
		return adresseResponse;
	}
}
