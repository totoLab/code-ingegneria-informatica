import terminale.Terminale;

public class Giocatore {
	
	private String nome;
	private String cognome;
	private int annoDiNascita;
	
	/* MUTATOR METHODS are insecure by definition, instead we better use a CONSTRUCTOR
	public void setNome(String nome) { // mutator method
		this.nome = nome;
	}
	
	public void setCognome(String cognome) { // mutator method
		this.cognome = cognome;
	}
	
	public void setAnnoNascita(int annoNascita) { // mutator method
		this.annoDiNascita = annoNascita;
	}
	*/
	// every object has a default constructor, but we can overwrite it with a custom one
	public Giocatore(String nome, String cognome, int annoNascita) { // same name as class
		this.nome = nome;
		this.cognome = cognome;
		this.annoDiNascita = annoNascita;
	}
	
	// standard printing for the object as String. Default exists and it's inherited from superclass constructor object
	public String toString() {
		return nome + " " + cognome + " nato nel " + annoDiNascita;

	}
	
	public String getNome() { // accessor method
		return nome; // returning pointer is not dangerous in this case, bc String objects are immutable
	}
	
	public String getCognome() { // accessor method
		return cognome;
	}
	
	public int getAnnoNascita() { // accessor method
		return annoDiNascita;
	}
	
	public boolean eGiovane () {
		return annoDiNascita > 1992;
	}
}
