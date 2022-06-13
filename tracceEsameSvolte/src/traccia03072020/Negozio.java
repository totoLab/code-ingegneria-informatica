package traccia03072020;

public class Negozio {

	private String nome;
	private String citta;
	
	public Negozio(String nome, String citta) {
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
		return "Negozio " + nome + " della città " + citta;
	}
	
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o == this) return true;
		if (!(o instanceof Negozio)) return false;
		
		Negozio n = (Negozio) o;
		return this.nome.equals(n.nome);
	}
	
}
