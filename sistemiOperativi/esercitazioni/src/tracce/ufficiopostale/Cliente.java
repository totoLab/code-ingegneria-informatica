package tracce.ufficiopostale;

import java.util.Random;

public class Cliente extends Thread {

    UfficioPostale ufficio;

    Cliente(UfficioPostale ufficio) {
        this.ufficio = ufficio;
    }

    @Override
    public void run() {
        try {
            Random random = new Random();
            String operazione = null;
            switch (random.nextInt(3)) {
                case 0:
                    operazione = "A";
                    break;
                case 1:
                    operazione = "B";
                    break;
                case 2:
                    operazione = "C";
                    break;
            }
            boolean disponibile = ufficio.ritiraTicket(operazione);
            if (disponibile) {
                ufficio.attendiSportello(operazione);
            }
            ufficio.log("Arrivederci", -1);
        } catch (InterruptedException e) { e.printStackTrace(); }
    }
}
