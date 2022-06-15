package traccia27012021;

import java.util.*;

public class Merce {
	
	private String nome;
	private String marca;
	private LinkedList<String> fornitori;
	
	public Merce(String nome, String marca, LinkedList<String> fornitori) {
		this.nome = nome;
		this.marca = marca;
		this.fornitori = new LinkedList<>(fornitori);
	}

	public String getNome() {
		return nome;
	}

	public String getMarca() {
		return marca;
	}

	public LinkedList<String> getGetFornitori() {
		return new LinkedList<>(fornitori);
	}
	
	public String toString() {
		return "Merce: " + nome + " di marca " + marca; 
	}

	public boolean equals(Object o) {
		if (o == null) return false;
		if (o == this) return true;
		if (!(o instanceof Merce)) return false;
		
		Merce m = (Merce) o;
		return this.nome.equals(m.nome) &&
				this.marca.equals(m.marca) &&
				this.fornitori.equals(m.fornitori);
	}
}
