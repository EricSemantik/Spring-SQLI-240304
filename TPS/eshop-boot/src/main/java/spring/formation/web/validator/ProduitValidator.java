package spring.formation.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.formation.model.Produit;

public class ProduitValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Produit.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Produit produit = (Produit) target;
		
		if(produit.getPrixAchat() == null ^ produit.getPrixVente() == null) {
			errors.rejectValue("prixAchat", "required",
					"Le prix d'achat doit être renseigné avec le prix de vente");
		} else if (produit.getPrixAchat() >= produit.getPrixVente()) {
			errors.rejectValue("prixVente", "supPrixAchat",
					"Le prix de vente doit être strictement supérieur au prix d'achat");
		}

	}

}
