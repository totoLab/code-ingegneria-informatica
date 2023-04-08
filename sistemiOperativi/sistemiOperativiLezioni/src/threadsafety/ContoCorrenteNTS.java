package threadsafety;

public class ContoCorrenteNTS extends ContoCorrente {
	
	public ContoCorrenteNTS(int depositoIniziale) {
		super(depositoIniziale);
	}

	@Override
	public void deposita(int importo) {
		this.deposito += importo;
	}
	
	@Override
	public void preleva(int importo) {
		this.deposito -= importo;
	}
}
