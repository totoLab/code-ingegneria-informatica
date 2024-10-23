package tracce.aziendaagricola;

import java.util.Random;

public class Cliente extends Thread {

    private int id;
    Random random = new Random();
    private AziendaAgricola azienda;

    Cliente(int id, AziendaAgricola azienda) {
        this.id = id;
        this.azienda = azienda;
    }

    @Override
    public void run() {
        int numeroSacchi = random.nextInt(1, 10);
        azienda.paga(id, numeroSacchi);
        azienda.prendiSacchi(id, numeroSacchi);
    }

}
