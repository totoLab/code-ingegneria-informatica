package traccia23072020;

public class Squadra {
	
	private String nome;
	private String citta;
	
	public Squadra(String nome, String citta) {
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
		return "Squadra " + nome + " della città " + citta;
	}
	
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o == this) return true;
		if (!(o instanceof Squadra)) return false;
		
		Squadra s = (Squadra) o;
		return this.nome.equals(s.nome);
	}
}
