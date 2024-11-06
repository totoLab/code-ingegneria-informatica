package tracce.supermercato;

import java.util.Random;

public class Cliente extends Thread {

    Cassa cassa;

    Cliente(Cassa cassa) {
        this.cassa = cassa;
    }

    @Override
    public void run() {
        try {
            Random random = new Random();
            int N = random.nextInt(1, 21);
            cassa.log("Faccio la spesa... " + N);
            cassa.attesa(2 * N);
            cassa.svuotaCarrello(N);
            cassa.paga(N);
            cassa.log("Arrivederci");
        } catch (InterruptedException e) { e.printStackTrace(); }
    }
}
