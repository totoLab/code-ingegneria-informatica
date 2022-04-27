package autostrade;

public class Citta {
	
	private String nome, provincia, regione;
	
	public Citta(String nome, String provincia, String regione) {
		this.nome = nome;
		this.provincia = provincia;
		this.regione = regione;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getProvincia() {
		return provincia;
	}
	
	public String getRegione() {
		return regione;
	}
	
	public String toString() {
		return nome + " (" + provincia + ") in " + regione;
	}
	
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (this == o) {
			return true;
		}
		if (!(o instanceof Citta)) {
			return false;
		}
		
		Citta c = (Citta) o;
		return this.nome.equals(c.nome) && this.provincia.equals(c.provincia);
	}
	
}
