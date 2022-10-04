package poo.app;

public class Monetina {
	public enum Esito{ TESTA, CROCE }
	private Esito faccia=
			Math.random()<0.5?Esito.TESTA:Esito.CROCE;
	public void lancia() {
		if( Math.random()<0.5 ) faccia=Esito.TESTA;
		else faccia=Esito.CROCE;
	}//lancia
	public Esito faccia() {
		return faccia;
	}//faccia
	public String toString() {
		return faccia.toString();
	}//toString
}//Monetina
