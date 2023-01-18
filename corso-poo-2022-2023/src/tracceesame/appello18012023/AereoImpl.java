package tracceesame.appello18012023;

import java.util.*;

public class AereoImpl extends AereoAbstract {

	HashMap<Posto, String> postiPasseggeri = new HashMap<>();
	HashMap<String, String> passeggeriBagagli = new HashMap<>();
	
	public AereoImpl(int file, int sedili) {
		super(file, sedili);
	}
	
	@Override
	public void prenotaPosto(String passeggero, Posto po, String bagaglio) throws IllegalArgumentException {
		if (!postoDisponibile(po) || postiPasseggeri.containsKey(po)) throw new IllegalArgumentException();
		postiPasseggeri.put(po, passeggero);
		if (bagaglio != null) passeggeriBagagli.put(passeggero, bagaglio);
	}

	@Override
	public String passeggeroDelPosto(Posto p) {
		if (postoLibero(p)) return null;
		return postiPasseggeri.get(p);
	}

	@Override
	public String cancellaPrenotazione(Posto p) {
		if (postoLibero(p)) return null;
		String passeggero = postiPasseggeri.get(p);
		postiPasseggeri.remove(p);
		passeggeriBagagli.remove(passeggero);
		return passeggero;
	}

	@Override
	public String bagaglioDelPasseggero(String passeggero) throws IllegalArgumentException {
		if (!esistePasseggero(passeggero)) throw new IllegalArgumentException();
		if (passeggeriBagagli.containsKey(passeggero)) return passeggeriBagagli.get(passeggero);
		return null;
	}
	
	private boolean esistePasseggero(String passeggero) {
		return passeggeri().contains(passeggero);
	}
	
	private boolean postoLibero(Posto p) {
		return !postiPasseggeri.containsKey(p);
	}
	
	private boolean postoDisponibile(Posto p) {
		return postiAereo().contains(p);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString() + "\n");
		for (Posto p : postiAereo()) {
			sb.append(p + ": ");
			if (!postoLibero(p)) {
				String passeggero = passeggeroDelPosto(p);
				sb.append(passeggero);
				String bagaglio = bagaglioDelPasseggero(passeggero);
				if (bagaglio != null) {
					sb.append(", con bagaglio: " + bagaglio);
				} else {
					sb.append(", senza bagaglio");
				}
			} else {
				sb.append("posto libero");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		Aereo a = new AereoImpl(2, 2);
		a.prenotaPosto("Antonio", "valigia");
		a.prenotaPosto("Giuseppe", "zaino");
		a.prenotaPosto("Mario", null);
		System.out.println(a);
	}

}
