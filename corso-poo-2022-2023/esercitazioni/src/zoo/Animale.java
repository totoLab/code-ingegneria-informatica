package zoo;

public abstract class Animale {
	
	protected String nome;
	protected String tipo; // mammifero e altre cose che mica lo so
	protected String habitat;
	
	
	
	public Animale(String nome, String tipo, String habitat) {
		this.nome = nome;
		this.tipo = tipo;
		this.habitat = habitat;
	}

	public abstract String verso();
}
