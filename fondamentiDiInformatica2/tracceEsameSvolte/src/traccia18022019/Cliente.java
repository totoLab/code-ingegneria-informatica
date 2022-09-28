package traccia18022019;

public class Cliente {
	
	private String nome;
	private String citta;
	
	public Cliente(String nome, String citta) {
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
		return "Cliente " + nome + " residente a " + citta;
	}
	
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o == this) return true;
		if (!(o instanceof Cliente)) return false;
		
		Cliente c = (Cliente) o;
		return this.nome.equals(c.nome);
	}
}
