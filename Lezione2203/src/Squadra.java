import java.util.*;
public class Squadra {
	private String nome;
	private ArrayList<Giocatore> giocatori;
	
	public Squadra(String nome) { // genera oggetti mutabili
		this.nome = nome;
		this.giocatori = new ArrayList<>();
	}
	
	public Squadra(Squadra s) {
		nome = s.nome;
		this.giocatori = new ArrayList<>(s.giocatori); // protective copy
	}
	
	public String toString() {
		String ret = "*** " + nome + " ***\n";
		for (Giocatore g : giocatori) {
			ret += g + "\n";
		}
		ret += "***********";
		return ret;
	}
	
	public void aggiungiGiocatore(Giocatore g) {
		this.giocatori.add(g);
	}
	
	public void rimuoviGiocatorePosizione(int i) {
		this.giocatori.remove(i);
	}
	
	public ArrayList<Giocatore> getGiocatori(){
		return new ArrayList<>(this.giocatori); // protective copy
	}
	
	public Giocatore getGiocatorePosizione(int i) {
		return giocatori.get(i); // Giocatore genera oggetti immutabili quindi niente protective copy
	}
	
	public void sostituisciGiocatorePosizione(Giocatore g, int i) {
		giocatori.set(i, g); // Giocatore genera oggetti immutabili quindi niente protective copy
	}
	
	public boolean contiene(Giocatore g) {
		return this.giocatori.contains(g);
	}
	
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (o == this) {
			return true;
		}
		if (!(o instanceof Squadra)) {
			return false;
		}
		
		Squadra s = (Squadra) o;
		return this.nome.equals(s.nome) && this.giocatori.equals(s.giocatori);
	}
	
	
}
