package tracce.callcenter;

public class Cliente extends Thread {

    CallCenter callCenter;
    int id;

    Cliente(CallCenter callCenter, int id) {
        this.callCenter = callCenter;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            callCenter.richiediAssistenza();
            callCenter.terminaChiamata();
        } catch (Exception e) { e.printStackTrace(); }
    }
}
