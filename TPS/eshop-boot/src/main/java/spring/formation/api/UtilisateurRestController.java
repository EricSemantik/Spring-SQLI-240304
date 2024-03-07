package spring.formation.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.formation.api.request.ConnexionRequest;
import spring.formation.api.response.ConnexionResponse;
import spring.formation.config.jwt.JWTUtils;

@RestController
@RequestMapping("/api/utilisateur")
public class UtilisateurRestController {

	@Autowired // Par défaut, ce manager n'existe pas dans le contexte, donc on le configure
				// dans SecurityConfig
	private AuthenticationManager authenticationManager;

	@PostMapping("/connexion")
	public ConnexionResponse connexion(@RequestBody ConnexionRequest connexionRequest) {
		// On va demander à SPRING SECURITY de vérifier le username / password
		// On a besoin d'un AuthenticationManager
		// On utilisera la méthode authenticate, qui attend un Authentication
		// Et on utilisera le type UsernamePasswordAuthenticationToken pour donner le
		// username & le password
		Authentication authentication = new UsernamePasswordAuthenticationToken(connexionRequest.getUsername(),
				connexionRequest.getPassword());

		// On demande à SPRING SECURITY de vérifier ces informations de connexion
		this.authenticationManager.authenticate(authentication);

		// Si on arrive ici, c'est que la connexion a fonctionné
		ConnexionResponse response = new ConnexionResponse();

		// On génère un jeton pour l'utilisateur connecté
		String token = JWTUtils.generate(authentication);

		response.setSuccess(true);
		response.setToken(token); // On donne le jeton en réponse

		return response;
	}

}
