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
}
