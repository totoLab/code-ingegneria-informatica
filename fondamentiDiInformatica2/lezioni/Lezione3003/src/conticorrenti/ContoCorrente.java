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
	
	public String toString() {
		String ret = "";
		ret += "Conto con codice IBAN "+IBAN+" intestato a "+intestatario+"\n";
        if(fido > 0) {
        	ret += "Fido: "+fido+"\n";
        }
        ret += "Saldo contabile: "+getSaldoContabile()+"\n";
        ret += "Saldo disponibile: "+getSaldoDisponibile()+"\n";
        if(!movimenti.isEmpty()) {
        	ret += "Lista dei movimenti:\n";
            for(Movimento m : movimenti) {
            	ret += m + "\n";
            }
        }
        return ret;     
    }

	public void setFido(float fido) {
		this.fido = fido;
	}
	
	public void aggiungiMovimento(Movimento m) {
		movimenti.add(m);
	}

	public String getIBAN() {
		return IBAN;
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
	
	public ArrayList<Movimento> getMovimenti() {
		return new ArrayList<>(this.movimenti);
	}

	public void setMovimenti(ArrayList<Movimento> movimenti) {
		this.movimenti = movimenti;
	}

	public String getIntestatario() {
		return intestatario;
	}

	public void setIntestatario(String intestatario) {
		this.intestatario = intestatario;
	}

	public boolean equals (Object o) {
		if (o == null) {
			return false;
		}
		if (o == this) {
			return true;
		}
		if (!(o instanceof ContoCorrente)) {
			return false;
		}
		ContoCorrente c = (ContoCorrente) o;
		return IBAN.equals(c.IBAN);
	}

	public void setIBAN(String iBAN) {
		IBAN = iBAN;
	}

}
