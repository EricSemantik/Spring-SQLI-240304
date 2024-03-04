package spring.formation;

import spring.formation.orchestre.Guitare;
import spring.formation.orchestre.Guitariste;
import spring.formation.orchestre.IInstrument;
import spring.formation.orchestre.Pianiste;
import spring.formation.orchestre.Piano;
import spring.formation.orchestre.Synthe;
import spring.formation.orchestre.Ukulele;

public class PrincipalJava {

	public static void main(String[] args) {
		// EXO 3 : Refaire en spring via annotation 
		IInstrument ukulele = new Ukulele();
		IInstrument piano = new Piano();
		IInstrument synthe = new Synthe();
		
		Pianiste pianiste = new Pianiste();
		pianiste.setInstrument(piano);
		pianiste.setMorceau("Bohemian Rhapsody");
		
		pianiste.jouer();
		
		Pianiste pianisteBis = new Pianiste(synthe, "Mais t'es où, pas là");
		
		pianisteBis.jouer();
		
		Pianiste pianisteTer = new Pianiste();
		pianisteTer.instrument = piano;
		pianisteTer.morceau = "Highway to hell";
		
		pianisteTer.jouer();
		
		// Fin EXO 3
		
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
		
		Guitariste guitaristeTer = new Guitariste(ukulele, "Merry Christmas !");
		
		guitaristeTer.jouer();
		// Fin EXO 2
	}

}
