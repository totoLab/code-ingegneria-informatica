package traccia27012021;

public class Fornitore {

	private String nome;
	private String citta;
	
	public Fornitore(String nome, String citta) {
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
		return "Fornitore " + nome + " con sede a " + citta;
	}
	
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o == this) return true;
		if (!(o instanceof Fornitore)) return false;
		
		Fornitore f = (Fornitore) o;
		return this.nome.equals(f.nome);
	}
}
