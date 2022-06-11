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
	
	public LinkedList<String> squadreCasalinghe() {
		LinkedList<String> squadre = new LinkedList<>();
		LinkedList<Integer> vittorie = new LinkedList<>();
		for (Partita p : listaPartite) {
			int differenzaReti = p.getGoalSquadraInCasa() - p.getGoalSquadraOspite();
			if (differenzaReti > 0) {
				String squadraCasa = p.getNomeSquadraInCasa();
				if (!(squadre.contains(squadraCasa)))
					squadre.add(squadraCasa);
			}
		}

		int max = -1;
		LinkedList<String> ret = new LinkedList<>();
		ListIterator<String> itSquadre = squadre.listIterator();
		ListIterator<Integer> itVittorie = vittorie.listIterator();
		for (; itSquadre.hasNext() && itVittorie.hasNext();) {
			String squadra = itSquadre.next();
			int vittoreDi = itVittorie.next();
			if (vittoreDi > max) {
				ret = new LinkedList<>();
				ret.add(squadra);
			} else if (vittoreDi == max) {
				ret.add(squadra);
			}
		}
		return ret;
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
