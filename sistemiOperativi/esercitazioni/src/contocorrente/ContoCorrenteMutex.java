package contocorrente;

import java.util.concurrent.Semaphore;

public class ContoCorrenteMutex extends ContoCorrente {

    Semaphore mutex = new Semaphore(1);

    @Override
    public void deposita(int importo) {
        try {
            mutex.acquire();
            super.saldo += importo;
            mutex.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void preleva(int importo) {
        try {
            mutex.acquire();
            super.saldo -= importo;
            mutex.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
