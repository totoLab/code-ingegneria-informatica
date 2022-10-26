package poo.util;

public class Monetina {
	
	public enum Esito{TESTA, CROCE};
	
	private Esito faccia;
	
	public Esito getFaccia() {
		return faccia;
	}
	
	public void lancia() {
		faccia = (Math.random()<0.5)?Esito.TESTA:Esito.CROCE;
	}
	
	public Monetina() {
		lancia();
	}
	

}
