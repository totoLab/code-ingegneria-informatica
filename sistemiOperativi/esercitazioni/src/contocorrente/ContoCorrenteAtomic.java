package contocorrente;

import java.util.concurrent.atomic.AtomicInteger;

public class ContoCorrenteAtomic extends ContoCorrente {

    private AtomicInteger saldo;

    ContoCorrenteAtomic(int saldo) {
        this.saldo = new AtomicInteger(saldo);
    }

    @Override
    public void deposita(int importo) {
        saldo.addAndGet(importo);
    }

    @Override
    public void preleva(int importo) {
        saldo.addAndGet(-importo);
    }

    @Override
    public int getSaldo() {
        return saldo.get();
    }
}
