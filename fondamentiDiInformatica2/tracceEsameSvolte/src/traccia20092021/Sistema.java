package traccia20092021;
import java.util.*;

import terminale.Terminale;

public class Sistema {

	private LinkedList<Tennista> listaTennisti;
	private LinkedList<Incontro> listaIncontri;
	
	public Sistema( LinkedList<Tennista> listaTennisti, LinkedList<Incontro> listaIncontri) {
		this.listaTennisti = new LinkedList<>(listaTennisti);
		this.listaIncontri = new LinkedList<>(listaIncontri);
	}
	
	private int almenoNVittorie(LinkedList<Integer> edizioniVittorie, int n) {
		int contatoreVittorie = 0;
		for (int edizione : edizioniVittorie) {
			if (edizione == n) {
				contatoreVittorie += 1;
			}
		}
		return contatoreVittorie;
	}
	
	public LinkedList<Integer> edizioniOK(String nomeTennista) {
		LinkedList<Integer> edizioniVittorie = new LinkedList<>();
		for (Incontro i : listaIncontri) {
			if (i.getVincitore().equals(nomeTennista)) {
				edizioniVittorie.add(i.getNumeroEdizione());
			}
		}
		LinkedList<Integer> ret = new LinkedList<>();
		for (int n : edizioniVittorie) {
			if (!(ret.contains(n)) && almenoNVittorie(edizioniVittorie, n) >= 2) {
				ret.add(n);
			}
		}
		return ret;
	}
	
	private LinkedList<Integer> edizioniAlmenoSemifinale(String nomeTennista) {
		LinkedList<Integer> ret = new LinkedList<>();
		for (Incontro i : listaIncontri) {
			int edizione = i.getNumeroEdizione();
			if ((i.getVincitore().equals(nomeTennista) || i.getVinto().equals(nomeTennista)) &&
					!(ret.contains(edizione)) &&
					almenoSemifinale(i.getFase()
							)) {
				ret.add(edizione);
			}
		}
		return ret;
	}
	
	private boolean almenoSemifinale(String fase) {
		return fase.equals("semifinale") || fase.equals("finale");
	}
	
	public LinkedList<String> tennistiFrequenti(int k) {
		LinkedList<String> ret = new LinkedList<>();
		for (Tennista t : listaTennisti) {
			LinkedList<Integer> edizioniAlmenoSemifinale = edizioniAlmenoSemifinale(t.getNome());
			if (edizioniAlmenoSemifinale.size() >= k) {
				ret.add(t.getNome());
			}
		}
		return ret;
	}
	
	private Tennista trovaTennista(String nomeTennista) {
		for (Tennista t : listaTennisti) {
			if (t.getNome().equals(nomeTennista)) {
				return t;
			}
		}
		return null;
	}
	
	private int vittorieInaspettate(Tennista t, int numEd1, int numEd2) {
		int contatore = 0;
		for (Incontro i : listaIncontri) {
			int numEdCorrente = i.getNumeroEdizione(); 
			if (numEd1 <= numEdCorrente && numEdCorrente <= numEd2) {
				String nomeTennista = t.getNome();
				Tennista vinto = trovaTennista(i.getVinto());
				if (nomeTennista.equals(i.getVincitore()) &&
						t.getClassifica() > vinto.getClassifica()) {
					contatore += 1;
				}
			}
		}
		return contatore;
	}
	
	public LinkedList<String> tennistiSorprendenti(int numEd1, int numEd2) {
		LinkedList<String> ret = new LinkedList<>();
		int max = -1;
		for (Tennista t : listaTennisti) {
			int vittorie = vittorieInaspettate(t, numEd1, numEd2);
			Terminale.stampa(vittorie);
			if (vittorie > max) {
				ret = new LinkedList<String>();
				ret.add(t.getNome());
				max = vittorie;
			} else if (vittorie == max) {
				ret.add(t.getNome());
			}
		}
		return ret;
	}
	
}
