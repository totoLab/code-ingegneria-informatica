package traccia18022019;
import java.util.*;

public class Articolo {
	
	private String codice;
	private ArrayList<Cliente> listaAcquirenti;
	private int data;
	
	public Articolo(String codice, ArrayList<Cliente> listaAcquirenti, int data) {
		this.codice = codice;
		this.listaAcquirenti = listaAcquirenti;
		this.data = data;
	}

	public String getCodice() {
		return codice;
	}
	
	public ArrayList<Cliente> getListaAcquirenti() {
		return listaAcquirenti;
	}
	
	public int getData() {
		return data;
	}
	
	public String toString() {
		return "Articolo cod. " + codice + " messo in vendita in data " + data;
	}
	
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o == this) return true;
		if (!(o instanceof Articolo)) return false;
		
		Articolo a = (Articolo) o;
		return this.codice.equals(a.codice);
	}
	
}
