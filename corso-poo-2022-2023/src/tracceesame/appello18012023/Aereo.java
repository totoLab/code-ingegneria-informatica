package tracceesame.appello18012023;

import java.util.*;

public interface Aereo {
	
	default Posto prenotaPosto(String passeggero, String bagaglio) {
		for (Posto p : postiAereo() ) {
			if (passeggeroDelPosto(p) == null) {
				prenotaPosto(passeggero, p, bagaglio);
				return p;
			}
		}
		return null;
	}
	
	void prenotaPosto(String passeggero, Posto po, String bagaglio) throws IllegalArgumentException;
	
	String passeggeroDelPosto(Posto p);
	
	String cancellaPrenotazione(Posto p);
	
	String bagaglioDelPasseggero(String passeggero) throws IllegalArgumentException; // abstract
	
	default List<String> passeggeri() {
		List<String> passeggeri = new ArrayList<>();
		for (Posto p : postiAereo()) {
			String passeggero = passeggeroDelPosto(p);
			if (passeggero != null) {
				passeggeri.add(passeggero);
			}
		}
		return passeggeri;
	}
	
	default List<String> bagagli() {
		List<String> bagagli = new ArrayList<>();
		for (String passeggero : passeggeri()) {
			String bagaglio = bagaglioDelPasseggero(passeggero);
			if (bagaglio != null) {
				bagagli.add(bagaglio);
			}
		}
		return bagagli;
	}
	
	List<Posto> postiAereo();
}
