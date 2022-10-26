package zoo;

public class Gorilla extends Animale {

	public Gorilla(String nome, String tipo, String habitat) {
		super(nome, tipo, habitat);
	}
	
	@Override
	public String verso() {
		return "Verso del gorilla";
	}
}
