package traccia23062021;

public class Operaio {

	private String nome;
	private int costoOrario;
	public Operaio(String nome, int costoOrario) {
		this.nome = nome;
		this.costoOrario = costoOrario;
	}
	public String getNome() {
		return nome;
	}
	public int getCostoOrario() {
		return costoOrario;
	}
	
	public String toString() {
		return "Operaio " + nome + " con costo orario " + costoOrario;
	}
	
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o == this) return true;
		if (!(o instanceof Operaio)) return false;
		
		Operaio op = (Operaio) o;
		return this.nome.equals(op.nome);
	}
}
