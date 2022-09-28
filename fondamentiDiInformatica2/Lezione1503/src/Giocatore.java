
public class Giocatore {
	
	private String nome;
	private String cognome;
	private int annoDiNascita;
	
	public Giocatore(String nome, String cognome, int annoNascita) {
		this.nome = nome;
		this.cognome = cognome;
		this.annoDiNascita = annoNascita;
	}
	
	public String toString() {
		return nome + " " + cognome + " nato nel " + annoDiNascita;

	}
	
	public boolean equals(Object o) { // status check
		if (o == null) {
			return false;
		} else if (o == this) {
			return true;
		} else if (!(o instanceof Giocatore)) {
			return false;
		}
			
		Giocatore g = (Giocatore) o; // casting
		return nome.equals(g.nome) && cognome.equals(g.cognome) && annoDiNascita == g.annoDiNascita; // comparing object requires equals(), primitives require ==
	}
	
	public String getNome() {
		return nome; 
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public int getAnnoNascita() {
		return annoDiNascita;
	}
	
	public boolean eGiovane () {
		return annoDiNascita > 1992;
	}
}
