package spring.formation.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RegisseurAspect {
	
	@Pointcut("execution(* spring.formation.orchestre.*.jouer(..))")
	public void pointCutJouer() {}

	@Before("execution(* spring.formation.orchestre.IInstrument+.toString(..))")
	public void accorder() {
		System.out.println("Le régisseur accorde l'instrument");
	}
	
	@Before("pointCutJouer()")
	public void ouvrirRideaux() {
		System.out.println("Le régisseur ouvre les rideaux");
	}
	
	@After("pointCutJouer()")
	public void fermerRideaux() {
		System.out.println("Le régisseur ferme les rideaux");
	}
	
	@Around("pointCutJouer()")
	public void gestionMicro(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		System.out.println("Le régisseur allume le micro");
		
		proceedingJoinPoint.proceed();
		
		System.out.println("Le régisseur éteint le micro");
	}
	
}
