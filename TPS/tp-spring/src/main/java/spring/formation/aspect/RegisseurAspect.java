package spring.formation.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RegisseurAspect {

	@Before("execution(* spring.formation.orchestre.*.jouer(..))")
	public void accorder() {
		System.out.println("Le régisseur accorde l'instrument");
	}
	
	public void ouvrirRideaux() {
		System.out.println("Le régisseur ouvre les rideaux");
	}
	
	public void fermerRideaux() {
		System.out.println("Le régisseur ferme les rideaux");
	}
	
}
