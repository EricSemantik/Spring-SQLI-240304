package spring.formation.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import spring.formation.orchestre.Guitare;
import spring.formation.orchestre.Guitariste;
import spring.formation.orchestre.IInstrument;
import spring.formation.orchestre.IMusicien;

@Configuration
@ComponentScan("spring.formation.orchestre")
public class ApplicationConfig {
	
	@Bean
	public IInstrument guitare() {
		return new Guitare();
	}
	
	@Bean
	public IMusicien guitariste(@Qualifier("guitare") IInstrument instrument) {
		Guitariste guitariste = new Guitariste();
		guitariste.setInstrument(instrument);
		guitariste.setMorceau("Vive le vent!");
		
		return guitariste;
	}
	
	@Bean
	public IMusicien guitaristeBis(@Qualifier("guitare") IInstrument instrument, @Value("Petit Papa Noël !") String morceau) {
		Guitariste guitariste = new Guitariste(instrument);
		guitariste.setMorceau(morceau);
		
		return guitariste;
	}
	
	@Bean
	public IMusicien guitaristeTer(@Qualifier("ukulele") IInstrument instrument) {
		return new Guitariste(instrument, "Merry Christmas !");
	}

}
