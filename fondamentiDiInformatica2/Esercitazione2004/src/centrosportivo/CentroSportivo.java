package centrosportivo;
import java.util.*;

import terminale.Terminale;

public class CentroSportivo {

	private ArrayList<Tesserato> listaTesserati;
	private ArrayList<Servizio> listaServizi;
	
	public CentroSportivo(ArrayList<Tesserato> listaTesserati, ArrayList<Servizio> listaServizi) {
		this.listaTesserati = new ArrayList<>(); 
		for (Tesserato t : listaTesserati) {
			this.listaTesserati.add(new Tesserato(t.getCf(), t.getNome(), t.getCognome(), t.getDataNascita(), t.getCodiceTessera(), t.getDataScadenza(), t.getListaAbbonamenti()));
		}
		this.listaServizi = new ArrayList<>();
		for (Servizio s : listaServizi) {
			this.listaServizi.add(new Servizio(s.getCodice(), s.getPostiTotali(), s.getPostiDisponibili(), s.getTurno(), s.getCostoSettimanale()));
		}
	}
	
	public boolean aggiorna(String codiceFiscale, int codServizio, String mese, boolean[] settimane) {
		Tesserato t = trovaTesserato(codiceFiscale);
		if (t == null) return false;
		Servizio s = trovaServizio(codServizio);
		if (s == null || !(s.verificaPostiDisponibili())) return false;
		
		s.aggiornaPostiDisponibili();
		
		boolean[][] mesiSettimane = new boolean[12][4];

		for(int i = 0; i < 12; i++) {
			for (int j = 0; j < 4; j++) {
				mesiSettimane[i][j] = false;
			}
		}
		int i = Abbonamento.getIndiceMese(mese);
		for (int j = 0; j < 4; j++) {
			mesiSettimane[i][j] = settimane[j];
		}
		Abbonamento a = new Abbonamento(codServizio, mesiSettimane);
		t.aggiungiAbbonamento(a);
		return true;
	}
	
	private Tesserato trovaTesserato(String cf) {
		for (Tesserato t : listaTesserati) {
			if (t.getCf() == cf) return t;
		}
		return null;
	}
	
	private Servizio trovaServizio(int codice) {
		for (Servizio s : listaServizi) {
			if (s.getCodice() == codice) return s;
		}
		return null;
	}
	
	public ArrayList<Servizio> serviziOrdinati() {
		ArrayList<Servizio> ret = new ArrayList<>(listaServizi);
		Collections.sort(ret);
		return ret;
	}
	
	public int[][] reportIscritti(String mese) {
		int[][] report = new int[listaTesserati.size()][2];
		int rigaVuota = 0;
		for (Tesserato t : listaTesserati) {
			ArrayList<Abbonamento> abbonamenti = t.getListaAbbonamenti();
			int totale = 0;
			for (Abbonamento a : abbonamenti) {
				Servizio s = trovaServizio(a.getCodiceServizio());
				int costoSett = s.getCostoSettimanale();
				int numSett = a.getSettimaneEffettive(mese);
				totale += costoSett * numSett;
			}
			report[rigaVuota][0] = t.getCodiceTessera();
			report[rigaVuota][1] = totale;
		}
		return report;
	}
}
