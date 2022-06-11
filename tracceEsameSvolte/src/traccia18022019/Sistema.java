package traccia18022019;
import java.util.*;

public class Sistema {
	
	private ArrayList<Cliente> listaClienti;
	private ArrayList<Articolo> listaArticoli;
	
	public Sistema(ArrayList<Cliente> listaClienti, ArrayList<Articolo> listaArticoli) {
		this.listaClienti = new ArrayList<>();
		this.listaClienti.addAll(listaClienti);
		this.listaArticoli = new ArrayList<>();
		this.listaArticoli.addAll(listaArticoli);
	}
	
	private boolean contieneClienti(ArrayList<Cliente> acquirenti, ArrayList<Cliente> clientiCitta) {
		for (Cliente c : clientiCitta) {
			if (!(acquirenti.contains(c))) return false;
		}
		return true;
	}
	
	private ArrayList<Cliente> residenti(String c) {
		ArrayList<Cliente> ret = new ArrayList<>();
		for (Cliente cl : listaClienti) {
			if (cl.getCitta().equals(c))
				ret.add(cl);
		}
		return ret;
	}
	
	public ArrayList<String> articoliCitta(String c) {
		ArrayList<String> ret = new ArrayList<>();
		for (Articolo a : listaArticoli) {
			ArrayList<Cliente> listaAcquirenti = a.getListaAcquirenti();
			if (contieneClienti(listaAcquirenti, residenti(c)))
				ret.add(a.getCodice());
		}
		return ret;
	}
	
	public ArrayList<Cliente> acquirentiUnici(int d1, int d2) {
		ArrayList<Cliente> listaUnici = new ArrayList<>();
		for (Articolo a : listaArticoli) {
			int data = a.getData();
			if ((d1 <= data && d2 >= data) && a.getListaAcquirenti().size() == 1) {
				listaUnici.add(a.getListaAcquirenti().get(0));
			}
		}
		return listaUnici;
	}
	
	private ArrayList<Articolo> synced_bubble_sort(ArrayList<Articolo> l1, ArrayList<Integer> l2) {
		for (int i = 0; i < l1.size() - 1; i++) {
			for (int j = 0; j < l1.size() - i - 1; j++) {
				if (l2.get(j) < l2.get(j + 1)) {
					Articolo a = l1.get(j);
					l1.set(j, l1.get(j + 1));
					l1.set(j + 1, a);
					
					int t = l2.get(j);
					l2.set(j, l2.get(j + 1));
					l2.set(j + 1, t);
				}
			}
		}
		return l1;
	}
	
	public ArrayList<Articolo> acquirentiComuni(Cliente a, Cliente b) {
		ArrayList<Articolo> ret = new ArrayList<>();
		ArrayList<Integer> date = new ArrayList<>();
		for (Articolo art : listaArticoli) {
			ArrayList<Cliente> acquirenti = art.getListaAcquirenti();
			if (acquirenti.contains(a) && acquirenti.contains(b)) {
				ret.add(art);
				date.add(art.getData());
			}
		}
		return synced_bubble_sort(ret, date);
	}
}
