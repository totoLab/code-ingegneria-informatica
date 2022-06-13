package traccia20092021;

public class Tennista {
	
	private String nome;
	private int classifica;
	
	public Tennista(String nome) {
		this.nome = nome;
		switch (nome) {
		case "Angela":
			classifica = 1;
			break;
		case "Beatrice":
			classifica = 2;
			break;
		case "Carla":
			classifica = 3;
			break;
		case "Daria":
			classifica = 4;
			break;
		case "Elisa":
			classifica = 5;
			break;
		}
	}
	
	public String getNome() {
		return nome;
	}
	
	public int getClassifica() {
		return classifica;
	}
	
	public String toString() {
		return "Tennista " + nome +
				" in posizione #" + classifica;
	}
	
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o == this) return true;
		if (!(o instanceof Tennista)) return false;
		
		Tennista t = (Tennista) o;
		return this.nome.equals(t.nome);
	}
}
