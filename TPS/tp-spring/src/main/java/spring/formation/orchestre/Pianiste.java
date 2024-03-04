package spring.formation.orchestre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("pianisteTer")
public class Pianiste implements IMusicien {

	@Autowired
	@Qualifier("piano")
	private IInstrument instrument;

	@Value("Highway to hell")
	private String morceau;

	public Pianiste() {
		super();
	}

	public Pianiste(IInstrument instrument) {
		super();
		this.instrument = instrument;
	}

//	@Autowired
	public Pianiste(@Qualifier("synthe") IInstrument instrument, @Value("Mais t'es où, pas là") String morceau) {
		super();
		this.instrument = instrument;
		this.morceau = morceau;
	}

	@Override
	public void jouer() {
		System.out.println("Le pianiste joue : " + this.morceau + "(" + this.instrument.toString() + ")");
		throw new RuntimeException("Fausse note");
	}

	public IInstrument getInstrument() {
		return instrument;
	}

//	@Autowired
//	@Qualifier("piano")
	public void setInstrument(IInstrument instrument) {
		this.instrument = instrument;
	}

	public String getMorceau() {
		return morceau;
	}

//	@Value("Bohemian Rhapsody")
	public void setMorceau(String morceau) {
		this.morceau = morceau;
	}

}
