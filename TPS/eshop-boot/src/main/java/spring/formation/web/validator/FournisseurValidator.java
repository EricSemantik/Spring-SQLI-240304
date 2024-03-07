package spring.formation.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.formation.model.Fournisseur;

public class FournisseurValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Fournisseur.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Fournisseur fournisseur = (Fournisseur) target;
		
		if(fournisseur.getAdresse().isBlank() && fournisseur.getResponsable().isBlank()) {
			errors.rejectValue("adresse", "required", "L'adresse Email ou le nom du responsable doivent être renseigné");
		}
		
	}

}
