package esercitazioni.zoo;

public class Leone extends Animale {

	private double velocitaMassima;
	
	public Leone(String nome, String tipo, String habitat, double velocitaMassima) {
		super(nome, tipo, habitat);
		this.velocitaMassima = velocitaMassima;
	}
	
	@Override
	public String verso() {
		return "Roaaaaaaaar";
	}
	
	
}
