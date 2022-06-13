package traccia03072020;
import java.util.*;

public class Sistema {
	
	private ArrayList<Negozio> listaNegozi;
	private ArrayList<Acquisto> listaAcquisti;

	public Sistema(ArrayList<Negozio> listaNegozi, ArrayList<Acquisto> listaAcquisti) {
		this.listaNegozi = new ArrayList<>(listaNegozi);
		this.listaAcquisti = new ArrayList<>(listaAcquisti);
	}
	
	
	
	private ArrayList<String> listaValoriMaggiori(ArrayList<String> chiavi, ArrayList<Integer> valori) {
		int max = -1;
		ArrayList<String> ret = new ArrayList<>();
		for (int i = 0; i < valori.size(); i++) {
			int valoreN = valori.get(i);
			if (valoreN > max) {
				ret = new ArrayList<>();
				ret.add(chiavi.get(i));
				max = valoreN;
			} else if (valoreN == max) {
				ret.add(chiavi.get(i));
			}
		}
		return ret;
	}
	
	public ArrayList<String> negoziPreferiti(int data) {
		ArrayList<String> negozi = new ArrayList<>();
		ArrayList<Integer> numeroAcquisti = new ArrayList<>();
		for (Acquisto a : listaAcquisti) {
			if (a.getData() == data) {
				String nomeNegozio = a.getNomeNegozio();
				int indiceNegozio = negozi.indexOf(nomeNegozio);
				if (!(negozi.contains(nomeNegozio))) {
					negozi.add(nomeNegozio);
					numeroAcquisti.add(1);
				} else {
					numeroAcquisti.set(indiceNegozio, numeroAcquisti.get(indiceNegozio) + 1);
				}
			}
		}
		return listaValoriMaggiori(negozi, numeroAcquisti);
	}
	
	private Negozio trovaNegozio(String nome) {
		for (Negozio n : listaNegozi) {
			if (n.getNome().equals(nome)) {
				return n;
			}
		}
		return null;
	}
	
	private ArrayList<String> estraiClienti() {
		ArrayList<String> ret = new ArrayList<>();
		for (Acquisto a : listaAcquisti) {
			String cf = a.getCFCliente();
			if (!(ret.contains(cf))) {
				ret.add(cf);
			}
		}
		return ret;
	}
	
	public ArrayList<String> clientiEsterniPeriodo(int dataInizio, int dataFine) {
		ArrayList<String> clienti = estraiClienti();
		for (Acquisto a : listaAcquisti) {
			int data = a.getData();
			if (dataInizio <= data && data <= dataFine) {
				String cittaNegozio = trovaNegozio(a.getNomeNegozio()).getCitta();
				if (a.getCittaCliente().equals(cittaNegozio)) {
					clienti.remove(a.getCFCliente());
				}
			}
		}		
		return clienti;
	}
	
	public ArrayList<String> clientiCittaDiverse() {
		ArrayList<String> clienti = new ArrayList<>();
		ArrayList<String> cittaClienti = new ArrayList<>();
		ArrayList<String> ret = new ArrayList<>();
		for (Acquisto a : listaAcquisti) {
			String cittaNegozio = trovaNegozio(a.getNomeNegozio()).getCitta();
			String cliente = a.getCFCliente();
			if (!(clienti.contains(cliente))) {
				clienti.add(cliente);
				cittaClienti.add(a.getCittaCliente());
			} else {
				if (!ret.contains(cliente) && !(a.getCittaCliente().equals(cittaNegozio))) {
					ret.add(cliente);
				}
			}
		}
		return ret;
	}
}
