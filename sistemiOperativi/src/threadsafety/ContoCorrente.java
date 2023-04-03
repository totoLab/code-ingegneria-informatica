package threadsafety;

abstract class ContoCorrente {
	
	protected int deposito;
	
	public ContoCorrente(int depositoIniziale) {
		this.deposito = depositoIniziale;
	}
	
	public abstract void deposita(int importo);
	
	public abstract void preleva(int importo);
	
	public int getDeposito() {
		return deposito;
	}
}
