package tracce.callcenter;

public class Operatore extends Thread {

    CallCenter callCenter;

    Operatore(CallCenter callCenter) {
        this.callCenter = callCenter;
    }

    @Override
    public void run() {
        try {
            int contatore = 0;
            while (true) {
                callCenter.fornisciAssistenza();
                contatore++;
                if (contatore % 15 == 0) {
                    callCenter.attesa(5);
                }
                callCenter.prossimoCliente();
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
}
