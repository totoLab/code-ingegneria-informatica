package traccia15022022;

import java.util.*;

public class Fattura {

	private int ID;
	private String cliente;
	private int data;
	private LinkedList<String> prodotti;
	private LinkedList<Integer> quantita;
	public int getID() {
		return ID;
	}
	public String getCliente() {
		return cliente;
	}
	public int getData() {
		return data;
	}
	public LinkedList<String> getProdotti() {
		return new LinkedList<>(prodotti);
	}
	public LinkedList<Integer> getQuantita() {
		return new LinkedList<>(quantita);
	}
	
	
}
