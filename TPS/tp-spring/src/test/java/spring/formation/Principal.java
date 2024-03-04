package spring.formation;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.formation.orchestre.IMusicien;

public class Principal {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		
		IMusicien guitariste = context.getBean(IMusicien.class);
		
		guitariste.jouer();
		
		
		context.close();
	}

}
