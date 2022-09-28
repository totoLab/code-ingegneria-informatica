package traccia23062021;

import java.util.LinkedList;

public class Sistema {
	
	LinkedList<Operaio> listaOperai;
	LinkedList<Intervento> listaInterventi;
	
	public Sistema(LinkedList<Operaio> listaOperai, LinkedList<Intervento> listaInterventi) {
		this.listaOperai = new LinkedList<>(listaOperai);
		this.listaInterventi = new LinkedList<>(listaInterventi);
	}
	
	private Operaio trovaOperaio(String nome) {
		for (Operaio o : listaOperai) {
			if (o.getNome().equals(nome)) {
				return o;
			}
		}
		return null;
	}
	
	private int costoTotaleIntervento(LinkedList<String> listaOperai, int durata) {
		int somma = 0;
		for (String nomeOperaio : listaOperai) {
			somma += durata * trovaOperaio(nomeOperaio).getCostoOrario();
		}
		return somma;
	}
	
	public String interventoPiuCostoso(int dataInizio, int dataFine) {
		int max = -1;
		String ret = listaInterventi.get(0).getCodice();
		for (Intervento i : listaInterventi) {
			int data = i.getData();
			if (dataInizio <= data && data <= dataFine) {
				int costo = costoTotaleIntervento(i.getListaOperai(), i.getDurata());
				String codice = i.getCodice();
				if (costo > max) {
					max = costo;
					ret = codice;
				}
			}
		}
		return ret;
	}
	
	private LinkedList<String> intersezione(LinkedList<String> l1, LinkedList<String> l2) {
		LinkedList<String> ret = new LinkedList<>();
		for (String s : l1) {
			if (l2.contains(s)) {
				ret.add(s);
			}
		}
		return ret;
	}
	
	public LinkedList<String> operaiSemprePresenti(int d) {
		LinkedList<String> ret = listaInterventi.get(0).getListaOperai();
		for (Intervento i : listaInterventi) {
			if (i.getDurata() >= d) {
				ret = intersezione(ret, i.getListaOperai());
			}
		}
		return ret;
	}
	
	private boolean interventi2ConDurateDiverse(String nomeOperaio) {
		boolean visto = false;
		int durata = -1;
		for (Intervento i : listaInterventi) {
			if (i.getListaOperai().contains(nomeOperaio)) {
				if (visto) {
					if (i.getDurata() != durata) {
						return true;
					}
				} else {
					visto = true;
					durata = i.getDurata();
				}
			}
		}
		return false;
	}
	
	public LinkedList<String> operaiUtilizzatiConDurateDiverse() {
		LinkedList<String> ret = new LinkedList<>();
		for (Operaio o : listaOperai) {
			if (interventi2ConDurateDiverse(o.getNome())) {
				ret.add(o.getNome());
			}
		}
		return ret;
	}
	
}
