package conticorrenti;
import java.util.*;
public class Banca { // oggetti mutabili
	
	private ArrayList<ContoCorrente> contiCorrenti;
	
	public Banca () {
		this.contiCorrenti = new ArrayList<>();
	}
	
	public Banca (Banca b) {
		// this.contiCorrenti = new ArrayList<>(b.contiCorrenti); // recursive copy of elements is based on pointers (shallow copy)
		
		// deep copy
		for (ContoCorrente c : b.contiCorrenti) {
			this.contiCorrenti.add(new ContoCorrente(c));
		}
	}
	
	public void aggiungiContoCorrente(ContoCorrente c) {
		this.contiCorrenti.add(new ContoCorrente(c));
	}
	
	public int numeroContiCorrenti() {
		return this.contiCorrenti.size();
	}
	
	public String clienteContoMax () {
		ContoCorrente contoMax = this.contiCorrenti.get(0);
		float saldoMax = contoMax.getSaldoContabile();
		for (int i = 1; i < this.contiCorrenti.size(); i++) {
			float saldo = contiCorrenti.get(i).getSaldoContabile();
			if (saldo > saldoMax) {
				contoMax = contiCorrenti.get(i);
				saldoMax = saldo;
			}
		}
		return contoMax.getIntestatario();
	}
	
	public ContoCorrente cerca (String IBAN) {
		for (ContoCorrente c : this.contiCorrenti) {
			if (c.getIBAN() == IBAN) {
				return new ContoCorrente(c);
			}
		}
		return null;		
	}
	
	public int contaDepositi() {
		int contatore = 0;
		for (ContoCorrente c : this.contiCorrenti) {
			for (Movimento m : c.getMovimenti()) {
				if (m.getTipo().equals("deposito")) {
					contatore++;
				}
			}
		}
		return contatore;
	}
	
	private static boolean contieneMovimentiTipo(ContoCorrente c, String tipo) {
		for (Movimento m : c.getMovimenti()) {
			if (m.getTipo().equals(tipo)) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<ContoCorrente> cercaContiSenzaMovimentiTipo(String tipo) {
		ArrayList<ContoCorrente> ret = new ArrayList<>();
		for (ContoCorrente c : contiCorrenti) {
			if (!contieneMovimentiTipo(c, tipo)) {
				ret.add(new ContoCorrente(c));
			}
		}
		return ret;
	}
	
	
}
