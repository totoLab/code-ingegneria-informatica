package traccia23062021;

import java.util.*;

public class Intervento {

	private String codice;
	private int data;
	private int durata;
	private LinkedList<String> listaOperai;
	
	public Intervento(String codice, int data, int durata, LinkedList<String> listaOperai) {
		this.codice = codice;
		this.data = data;
		this.durata = durata;
		this.listaOperai = listaOperai;
	}

	public String getCodice() {
		return codice;
	}

	public int getData() {
		return data;
	}

	public int getDurata() {
		return durata;
	}

	public LinkedList<String> getListaOperai() {
		return new LinkedList<>(listaOperai);
	}
	
	public String toString() {
		return "Intervento con codice " + codice +
				" effettuato in data " + data +
				" della durata di " + durata;
	}
	
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o == this) return true;
		if (!(o instanceof Intervento)) return false;
		
		Intervento i = (Intervento) o;
		return this.codice.equals(i.codice) &&
				this.data == i.data &&
				this.durata == i.durata &&
				this.listaOperai.equals(i.listaOperai);
	}
}
