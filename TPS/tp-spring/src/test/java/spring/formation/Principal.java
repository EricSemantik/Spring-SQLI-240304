package spring.formation;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.formation.orchestre.IMusicien;

public class Principal {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

		IMusicien guitariste = context.getBean("guitariste", IMusicien.class);

		guitariste.jouer();

		IMusicien guitaristeBis = (IMusicien) context.getBean("guitaristeBis");

		guitaristeBis.jouer();
		
		IMusicien guitaristeTer = context.getBean("guitaristeTer", IMusicien.class);

		guitaristeTer.jouer();

		context.close();
	}

}
