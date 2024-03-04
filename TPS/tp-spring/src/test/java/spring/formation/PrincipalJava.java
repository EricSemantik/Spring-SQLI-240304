package spring.formation;

import spring.formation.orchestre.Guitare;
import spring.formation.orchestre.Guitariste;
import spring.formation.orchestre.IInstrument;
import spring.formation.orchestre.IMusicien;

public class PrincipalJava {

	public static void main(String[] args) {
		// EXO 1 : Refaire en spring 
		IInstrument guitare = new Guitare();
		
		Guitariste guitariste = new Guitariste();
		guitariste.setInstrument(guitare);
		guitariste.setMorceau("Vive le vent !");
		
		guitariste.jouer();
		// Fin EXO 1

		// EXO 2 : Refaire en spring et rechercher sur internet
		Guitariste guitaristeBis = new Guitariste(guitare);
		guitaristeBis.setMorceau("Petit Papa Noël !");
		
		guitaristeBis.jouer();
		
		Guitariste guitaristeTer = new Guitariste(guitare, "Merry Christmas !");
		
		guitaristeTer.jouer();
		// Fin EXO 2
	}

}
