package conticorrenti;
import java.util.*;

public class ContoCorrente {

	private String IBAN; // key element - attributo identificatore
	/* non possono esistere due oggetti diversi con lo stesso IBAN
	 * se ci sono due oggetti con lo stesso IBAN e diversi valori per altri attributi ci dice che c'è un errore
	 * solitamente ci sono INTEGRITY CONSTRAINTS per non farlo accadere)
	 * noi assumiamo che i dati siano consistenti */
	private String intestatario;
	private ArrayList<Movimento> movimenti;
	private float fido;

	public ContoCorrente(String IBAN, String intestatario) {
		this.IBAN = IBAN;
		this.intestatario = intestatario;
		this.movimenti = new ArrayList<>();
		this.fido = 0;
	}
	
	public ContoCorrente(ContoCorrente c) {
		this.IBAN = c.IBAN;
		this.intestatario = c.intestatario;
		this.movimenti = new ArrayList<>(c.movimenti);		
		this.fido = c.fido;
	}	

	public void setFido(float fido) {
		this.fido = fido;
	}
	
	public void aggiungiMovimento(Movimento m) {
		movimenti.add(m);
	}

	public float getSaldoContabile() {
		float somma = 0;
		for (Movimento m : movimenti) {
			somma += m.getValore();
		}
		return somma;
	}
	
	public float getSaldoDisponibile() {
		return getSaldoContabile() + this.fido;
	}

}
