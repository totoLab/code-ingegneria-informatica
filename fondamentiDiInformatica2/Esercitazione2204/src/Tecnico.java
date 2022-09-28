
public class Tecnico {
	
	private String nome;
	private int costoOrario;
	
	public Tecnico(String nome, int costoOrario) {
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
		return "Tecnico " + nome + " dal costo orario di " + costoOrario + " euro.";
	}
	
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (this == o)
			return true;
		if (!(o instanceof Tecnico))
			return false;
		
		Tecnico t = (Tecnico) o;
		return nome.equals(t.getNome()); 
	}
}
