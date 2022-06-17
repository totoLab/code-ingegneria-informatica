package traccia17062022;

import java.util.*;

public class Sistema {

	private LinkedList<Volo> listaVoli;
	private LinkedList<Prenotazione> listaPrenotazioni;
	
	public Sistema(LinkedList<Volo> listaVoli, LinkedList<Prenotazione> listaPrenotazioni) {
		this.listaVoli = new LinkedList<>(listaVoli);
		this.listaPrenotazioni = new LinkedList<>(listaPrenotazioni);
	}
	
	public boolean verificaPrenotazioni() {
		for (Prenotazione p : listaPrenotazioni) {
			if (!prenotazioneValida(p.getPercorso())) {
				return false;
			}
		}
		return true;
	}

	private boolean prenotazioneValida(LinkedList<String> percorso) {
		ListIterator<String> it = percorso.listIterator();
		for (; it.hasNext(); ) {
			String partenza = it.next();
			if (it.hasNext()) {
				String arrivo = it.next();
				if (!voloValido(partenza, arrivo)) {
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean voloValido(String partenza, String arrivo) {
		for (Volo v : listaVoli) {
			String partenzaV = v.getPartenza();
			String arrivoV = v.getArrivo();
			if (partenzaV.equals(partenza) && arrivoV.equals(arrivo)) {
				return true;
			}
		}
		return false;
	}
	
	public Volo voloMax() {
		Volo ret = listaVoli.get(0);
		int max = -1;
		for (Volo v : listaVoli) {
			int incasso = calcolaIncasso(v);
			if (incasso > max) {
				ret = v;
				max = incasso;
			}
		}
		return ret;
	}

	private int calcolaIncasso(Volo v) {
		int incasso = 0;
		for (Prenotazione p : listaPrenotazioni) {
			if (contieneVolo(p.getPercorso(), v)) {
				if (p.getClasse().equals("business")) {
					incasso += v.getPrezzoBusiness();
				} else if (p.getClasse().equals("economica")) {
					incasso += v.getPrezzoEconomica();
				}
			}
		}
		return incasso;
	}

	private boolean contieneVolo(LinkedList<String> percorso, Volo v) {
		String partenzaV = v.getPartenza();
		String arrivoV = v.getArrivo();
		ListIterator<String> it = percorso.listIterator();
		for (; it.hasNext(); ) {
			String partenza = it.next();
			if (it.hasNext()) {
				String arrivo = it.next();
				if (partenzaV.equals(partenza) && arrivoV.equals(arrivo)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public LinkedList<String> destinazioneComune(String cliente) {
		LinkedList<String> ret = new LinkedList<>();
		LinkedList<String> destinazioni = trovaDestinazioni(cliente);
		for (Prenotazione p : listaPrenotazioni) {
			String clienteP = p.getNomeCliente();
			if (!clienteP.equals(cliente) &&
				!ret.contains(clienteP) &&
				destinazioni.contains(p.getPercorso().getLast())) {
					ret.add(clienteP);
			}
		}
		return ret;
	}

	private LinkedList<String> trovaDestinazioni(String cliente) {
		LinkedList<String> ret = new LinkedList<>();
		for (Prenotazione p : listaPrenotazioni) {
			String destinazione = p.getPercorso().getLast();
			if (p.getNomeCliente().equals(cliente) &&
				!ret.contains(destinazione)) {
				ret.add(destinazione);
			}
		}
		return ret;
	}
}
