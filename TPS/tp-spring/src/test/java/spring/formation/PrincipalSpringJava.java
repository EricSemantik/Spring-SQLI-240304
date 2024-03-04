package spring.formation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.formation.config.ApplicationConfig;
import spring.formation.orchestre.IMusicien;

public class PrincipalSpringJava {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		
//		IMusicien guitariste = context.getBean("guitariste", IMusicien.class);
//
//		guitariste.jouer();
//
//		IMusicien guitaristeBis = (IMusicien) context.getBean("guitaristeBis");
//
//		guitaristeBis.jouer();
		
		IMusicien guitaristeTer = context.getBean("guitaristeTer", IMusicien.class);

		guitaristeTer.jouer();
		
		IMusicien pianiste = context.getBean("pianisteTer", IMusicien.class);

		pianiste.jouer();

		context.close();
	}

}
