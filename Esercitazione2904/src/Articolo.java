import java.util.*;

public class Articolo {

	private String nome;
	private float prezzo;
	private LinkedList<String> componenti;
	
	public Articolo(String nome, float prezzo, LinkedList<String> componenti) {
		this.nome = nome;
		this.prezzo = prezzo;
		this.componenti = componenti;
	}

	public String getNome() {
		return nome;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public LinkedList<String> getComponenti() {
		LinkedList<String> componenti = new LinkedList<>();
		for (String s : this.componenti) {
			componenti.addLast(s);
		}
		return componenti;
	}

	public String toString() {
		String ret = "Articolo " + nome + " con prezzo " + prezzo;
		for (String s : componenti) {
			ret += s + "\n";
		}
		return ret;
	}
	
	public boolean equals(Object o) {
		if (o == null) return false;
		if (this == o) return true;
		if (!(o instanceof Articolo)) return false;
		
		Articolo a = (Articolo) o;
		return this.nome.equals(a.nome);
	}
}
