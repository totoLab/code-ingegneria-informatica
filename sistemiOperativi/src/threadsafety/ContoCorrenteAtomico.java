package threadsafety;

import java.util.concurrent.atomic.AtomicInteger;

public class ContoCorrenteAtomico extends ContoCorrente {

	private AtomicInteger deposito;
	
	public ContoCorrenteAtomico(int depositoIniziale) {
		super(depositoIniziale);
		deposito = new AtomicInteger(depositoIniziale);
	}
	
	@Override
	public void deposita(int importo) {
		deposito.addAndGet(importo);
	}
	
	@Override
	public void preleva(int importo) {
		deposito.addAndGet(-importo);
	}
	
	@Override
	public int getDeposito() {
		return deposito.get();
	}
	
}
