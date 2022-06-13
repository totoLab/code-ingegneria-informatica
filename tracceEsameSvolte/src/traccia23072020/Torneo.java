package traccia23072020;
import java.util.*;

public class Torneo {
	
	LinkedList<Squadra> listaSquadre;
	LinkedList<Partita> listaPartite;
	
	public Torneo(LinkedList<Squadra> listaSquadre, LinkedList<Partita> listaPartite) {
		this.listaSquadre = new LinkedList<>();
		this.listaSquadre.addAll(listaSquadre);
		this.listaPartite = new LinkedList<>();
		this.listaPartite.addAll(listaPartite);
	}

	public LinkedList<Squadra> getListaSquadre() {
		LinkedList<Squadra> listaSquadre = new LinkedList<>();
		listaSquadre.addAll(this.listaSquadre);
		return listaSquadre;
	}

	public LinkedList<Partita> getListaPartite() {
		LinkedList<Partita> listaPartite = new LinkedList<>();
		listaPartite.addAll(this.listaPartite);
		return listaPartite;
	}
	
	private LinkedList<String> listaChiaviAValoriMaggiori(LinkedList<String> chiavi, LinkedList<Integer> valori) {
		int max = -1;
		LinkedList<String> ret = new LinkedList<>();
		ListIterator<String> itChiavi = chiavi.listIterator();
		ListIterator<Integer> itValori = valori.listIterator();
		for (; itChiavi.hasNext() && itValori.hasNext();) {
			String chiave = itChiavi.next();
			int valore = itValori.next();
			if (valore > max) {
				ret = new LinkedList<>();
				ret.add(chiave);
				max = valore;
			} else if (valore == max) {
				ret.add(chiave);
			}
		}
		return ret;
	}

	private int contaVittorie(String squadra) {
		int vittorie = 0;
		for (Partita p : listaPartite) {
			if (squadra.equals(p.getNomeSquadraInCasa()) && p.getGoalSquadraInCasa() > p.getGoalSquadraOspite()) {
				vittorie += 1;
			}
		}
		return vittorie;
	}

	private LinkedList<String> estraiNomiSquadre() {
		LinkedList<String> ret = new LinkedList<>();
		for (Squadra s : listaSquadre) {
				ret.add(s.getNome());
		}
		return ret;
	}

	public LinkedList<String> squadreCasalinghe() {
		LinkedList<String> squadre = estraiNomiSquadre();
		LinkedList<Integer> vittorie = new LinkedList<>();
		for (int i = 0; i < squadre.size(); i++) vittorie.add(0);
		for (String s : squadre) {
			int vittorieS = contaVittorie(s);
			int indice = squadre.indexOf(s);
			vittorie.set(indice, vittorieS);
		}
		return listaChiaviAValoriMaggiori(squadre, vittorie);
	}
	
	private Squadra trovaSquadra(String nomeSquadraInCasa) {
		for (Squadra s : listaSquadre) {
			if (s.getNome().equals(nomeSquadraInCasa)) {
				return s;
			}
		}
		return null;
	}
	
	private LinkedList<Partita> trovaPartiteArbitro(String nomeArbitro) {
		LinkedList<Partita> ret = new LinkedList<>();
		for (Partita p : listaPartite) {
			if (p.getNomeArbitro().equals(nomeArbitro)) {
				ret.add(p);
			}
		}
		return ret;
	}
	
	private LinkedList<String> estraiNomiArbitri() {
		LinkedList<String> ret = new LinkedList<>();
		for (Partita p : listaPartite) {
			String nomeArbitro = p.getNomeArbitro();
			if (!(ret.contains(nomeArbitro))) {
				ret.add(nomeArbitro);
			}
		}
		return ret;
	}
	
	public LinkedList<String> arbitriFuoriCItta() {
		LinkedList<String> ret = new LinkedList<>();
		for (String a : estraiNomiArbitri()) {
			boolean stessaCitta = false;
			for (Partita p : trovaPartiteArbitro(a)) {
				String cittaPartita = trovaSquadra(p.getNomeSquadraInCasa()).getCitta();
				if (p.getCittaArbitro().equals(cittaPartita)) {
					stessaCitta = true;
				}
			}
			if (!stessaCitta) {
				ret.add(a);
			}
		}
		return ret;
	}

	public LinkedList<String> arbitri3squadre() {
		LinkedList<String> ret = new LinkedList<>();
		for (String a : estraiNomiArbitri()) {
			LinkedList<String> partiteDiverseArbitrate = new LinkedList<>();
			for (Partita p : trovaPartiteArbitro(a)) {
				String squadraCasa = p.getNomeSquadraInCasa();
				String squadraOspite = p.getNomeSquadraOspite();
				if (!(partiteDiverseArbitrate.contains(squadraCasa))) {
					partiteDiverseArbitrate.add(squadraCasa);
				} else if (!(partiteDiverseArbitrate.contains(squadraOspite))) {
					partiteDiverseArbitrate.add(squadraOspite);
				}
			}
			if (partiteDiverseArbitrate.size() >= 3) {
				ret.add(a);
			}
		}
		return ret;
	}	
}
