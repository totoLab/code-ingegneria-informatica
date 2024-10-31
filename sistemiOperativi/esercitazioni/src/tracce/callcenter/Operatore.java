package tracce.callcenter;

public class Operatore extends Thread {

    CallCenter callCenter;

    Operatore(CallCenter callCenter) {
        this.callCenter = callCenter;
    }

    @Override
    public void run() {
        try {
            while (true) {
                callCenter.fornisciAssistenza();
                callCenter.prossimoCliente();
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
}
