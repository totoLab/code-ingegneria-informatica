package traccia16092020;

public class Utente {
	
	private String nome;
	private String citta;
	
	public Utente(String nome, String citta) {
		this.nome = nome;
		this.citta = citta;
	}

	public String getNome() {
		return nome;
	}
	
	public String getCitta() {
		return citta;
	}

	public String toString() {
		return "Utente " + nome + " residente a " + citta;
	}
	
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o == this) return true;
		if (!(o instanceof Utente)) return false;
		
		Utente u = (Utente) o;
		return this.nome.equals(u.nome);
	}
	
	
	
		
}
