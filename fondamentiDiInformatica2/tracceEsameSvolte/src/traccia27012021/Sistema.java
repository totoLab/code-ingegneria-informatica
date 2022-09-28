package traccia27012021;

import java.util.*;

public class Sistema {

	private LinkedList<Fornitore> listaFornitori;
	private LinkedList<Merce> listaMerci;
	
	public Sistema(LinkedList<Fornitore> listaFornitori, LinkedList<Merce> listaMerci) {
		this.listaFornitori = new LinkedList<>(listaFornitori);
		this.listaMerci = new LinkedList<>(listaMerci);
	}
	
	private int calcolaNumeroMerciFornite(String nomeFornitore) {
		int cont = 0;
		for (Merce m : listaMerci) {
			LinkedList<String> fornitoriM = m.getGetFornitori();
			if (fornitoriM.contains(nomeFornitore)) {
				cont += 1;
			}
		}
		return cont;
	}
	
	public LinkedList<String> grandiFornitori() {
		LinkedList<String> ret = new LinkedList<>();
		int max = -1;
		for (Fornitore f : listaFornitori) {
			String nomeFornitore = f.getNome();
			int merciFornite = calcolaNumeroMerciFornite(nomeFornitore);
			if (merciFornite > max) {
				ret = new LinkedList<>();
				ret.add(nomeFornitore);
				max = merciFornite;
			} else if (merciFornite == max) {
				ret.add(nomeFornitore);
			}
		}
		return ret;
	}

	private boolean monoMarca(String marca, String nomeFornitore) {
		for (Merce m : listaMerci) {
			LinkedList<String> fornitoriM = m.getGetFornitori();
			if (fornitoriM.contains(nomeFornitore)) {
				if (!(m.getMarca().equals(marca))) {
					return false;
				}
			}
		}
		return true;
	}
	
	public LinkedList<String> fornitoriMonoMarca(String marca) {
		LinkedList<String> ret = new LinkedList<>();
		for (Fornitore f : listaFornitori) {
			String nomeFornitore = f.getNome();
			if (monoMarca(marca, nomeFornitore)) {
				ret.add(nomeFornitore);
			}
		}
		return ret;
	}
	
	private Fornitore trovaFornitore(String nomeFornitore) {
		for (Fornitore f : listaFornitori) {
			if (f.getNome().equals(nomeFornitore)) {
				return f;
			}
		}
		return null;
	}
	
	private boolean cittaDiverse(LinkedList<String> nomiFornitori) {
		String citta = trovaFornitore(nomiFornitori.get(0)).getCitta();
		for (String nome : nomiFornitori) {
			Fornitore f = trovaFornitore(nome);
			if (!(f.getCitta().equals(citta))) {
				return true;
			}
		}
		return false;
	}
	
	public LinkedList<String> merciCittaDiverse() {
		LinkedList<String> ret = new LinkedList<>();
		for (Merce m : listaMerci) {
			if (cittaDiverse(m.getGetFornitori())) {
				ret.add(m.getNome());
			}
		}
		return ret;
	}
}
