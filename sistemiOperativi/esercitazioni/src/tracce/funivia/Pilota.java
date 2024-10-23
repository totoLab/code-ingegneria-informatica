package tracce.funivia;

public class Pilota extends Thread {

    Funivia funivia;

    public Pilota(Funivia funivia) {
        this.funivia = funivia;
    }

    @Override
    public void run() {
        while (true) {
            try {
                funivia.pilotaStart();
                funivia.pilotaEnd();
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}
