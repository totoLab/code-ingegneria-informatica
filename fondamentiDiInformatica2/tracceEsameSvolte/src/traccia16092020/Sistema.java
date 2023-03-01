package traccia16092020;
import java.util.*;

public class Sistema {

	ArrayList<Utente> listaUtenti;
	ArrayList<Messaggio> listaMessaggi;
	
	public Sistema(ArrayList<Utente> listaUtenti, ArrayList<Messaggio> listaMessaggi) {
		this.listaUtenti = new ArrayList<>();
		this.listaUtenti.addAll(listaUtenti);
		this.listaMessaggi = new ArrayList<>();
		this.listaMessaggi.addAll(listaMessaggi);
	}
	
//	public ArrayList<String> nessunaLettura(String destinatario) {
//		ArrayList<String> mittentiIgnorati = new ArrayList<>();
//		for (Messaggio m : listaMessaggi) {
//			if (m.getNomeDestinatario().equals(destinatario) &&
//					!mittentiIgnorati.contains(m.getNomeMittente())
//					&& !m.letto()) {
//				mittentiIgnorati.add(m.getNomeMittente());
//			}
//		}
//		return mittentiIgnorati;
//	}
	
	public ArrayList<String> nessunaLettura(String destinatario) {
		ArrayList<String> ret = new ArrayList<>();
		for (Utente u : listaUtenti) {
			if (nessunaLetturaUtente(u, destinatario)) {
				ret.add(u.getNome());
			}
		}
		return ret;
	}
	
	public boolean nessunaLetturaUtente(Utente u, String destinatario) {
		for (Messaggio m : listaMessaggi) {
			String nomeUtente = u.getNome();
			if (nomeUtente.equals(m.getNomeMittente()) &&
					destinatario.equals(m.getNomeDestinatario())) {
				if (m.letto()) {
					return false;
				}
			}
		}
		return true;
	}
	

	public ArrayList<String> cittaUnica(int dataInizio, int dataFine) {
		ArrayList<String> mittentiBravi = new ArrayList<>();
		ArrayList<Integer> numeroMessaggi = new ArrayList<>();
		ArrayList<String> cittaDestinatari = new ArrayList<>();
		for (Messaggio m : listaMessaggi) {
			int data = m.getData();
			if (dataInizio <= data && data <= dataFine) {
				String mittente = m.getNomeMittente();
				String cittaDestinatario = trovaUtente(m.getNomeDestinatario()).getCitta();
				int indiceMittente = mittentiBravi.indexOf(mittente);
				if (indiceMittente != -1) {
					boolean cittaUguale = cittaDestinatario.equals(cittaDestinatari.get(indiceMittente));
					if (cittaUguale) {
						numeroMessaggi.set(indiceMittente, numeroMessaggi.get(indiceMittente) + 1);
					} else {
						numeroMessaggi.set(indiceMittente, -2000000000);
					}
				} else {
					mittentiBravi.add(mittente);
					numeroMessaggi.add(1);
					cittaDestinatari.add(cittaDestinatario);
				}
			}
		}
		ArrayList<String> ret = new ArrayList<>();
		for (int i = 0; i < mittentiBravi.size(); i++) {
			if (numeroMessaggi.get(i) >= 2) {
				ret.add(mittentiBravi.get(i));
			}
		}
		return ret;
	}

	private Utente trovaUtente(String nomeDestinatario) {
		for (Utente u : listaUtenti) {
			if (u.getNome().equals(nomeDestinatario)) {
				return u;
			}
		}
		return null;
	}
	
	public ArrayList<String> mittentiFrequenti(int data, String citta) {
		ArrayList<String> mittenti = new ArrayList<>();
		ArrayList<Integer> numeroMessaggi = new ArrayList<>();
		for (Messaggio m : listaMessaggi) {
			String mittente = m.getNomeMittente();
			if (m.getData() == data && trovaUtente(m.getNomeDestinatario()).getCitta().equals(citta)) {
				int indiceMittente = mittenti.indexOf(mittente);
				if (indiceMittente == -1) {
					mittenti.add(mittente);
					numeroMessaggi.add(1);
				} else {
					numeroMessaggi.set(indiceMittente, numeroMessaggi.get(indiceMittente) + 1);
				}
			}
		}
		
		int max = -1;
		ArrayList<String> ret = new ArrayList<>();
		for (int i = 0; i < mittenti.size(); i++) {
			String mittente = mittenti.get(i);
			int messaggiUtente = numeroMessaggi.get(i);
			if (messaggiUtente > max) {
				ret = new ArrayList<>();
				ret.add(mittente);
				max = messaggiUtente;
			} else if (messaggiUtente == max) {
				ret.add(mittente);
			}
		}
		return ret;
	}
}
