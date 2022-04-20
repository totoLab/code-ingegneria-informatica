package centrosportivo;
import java.util.*;

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
		
	}
	
	private Tesserato trovaTesserato(String cf) {
		
	}
	
	private Servizio trovaServizio(int codice) {
		
	}
	
	public ArrayList<Servizio> serviziOrdinati() {
		
	}
	
	public int[][] reportIscritti(String mese) {
		
	}
}
