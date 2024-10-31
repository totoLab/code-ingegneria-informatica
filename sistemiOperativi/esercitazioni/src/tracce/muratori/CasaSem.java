package tracce.muratori;

import java.util.concurrent.Semaphore;

public class CasaSem extends Casa {

    Semaphore[] turno = new Semaphore[] {
            new Semaphore(1, true),
            new Semaphore(0, true),
    };
    Semaphore mutex = new Semaphore(1);
    int fileDepositate = 0;

    int ultimoChiudeLaPorta = 0;

    CasaSem() {
        super();
    }

    @Override
    boolean inizia(int t) throws InterruptedException {
        log(t, "preparazione materiale.");
        attesa(preparazioneMateriale[t]);
        log(t, "fine preparazione materiale.");

        turno[t].acquire();
        mutex.acquire();
        ultimoChiudeLaPorta++;
        if (fileDepositate == N) {
            turno[1 - t].release();
            if (ultimoChiudeLaPorta == 1) {
                turno[t].release();
            }
            mutex.release();
            return false;
        }
        mutex.release();

        return true;
    }

    @Override
    void termina(int t) throws InterruptedException {
        mutex.acquire();
        ultimoChiudeLaPorta--;
        if (t == 1) {
            fileDepositate++;
            log(t, fileDepositate + " file depositate.");
        }
        mutex.release();

        // lascia lavorare gli altri
        turno[1 - t].release();
        attesa(5); // riposo
    }

    public static void main(String[] args) {
        Casa casa = new CasaSem();
        casa.test(7, 5);
    }
}
