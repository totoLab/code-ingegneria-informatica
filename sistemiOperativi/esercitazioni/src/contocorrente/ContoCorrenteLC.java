package contocorrente;

import java.util.concurrent.locks.*;

public class ContoCorrenteLC extends ContoCorrente {

    private Lock lock = new ReentrantLock();

    @Override
    public void deposita(int importo) {
        lock.lock();
        try {
            saldo += importo;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void preleva(int importo) {
        lock.lock();
        try {
            saldo -= importo;
        } finally {
            lock.unlock();
        }
    }
}
