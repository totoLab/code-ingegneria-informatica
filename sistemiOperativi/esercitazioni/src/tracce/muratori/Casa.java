package tracce.muratori;

import java.util.concurrent.TimeUnit;

public abstract class Casa {

    protected final int NUM_PARETI = 4;
    protected final int[] mattoniPareti = new int[NUM_PARETI];
    protected final int N = 7;
    protected final int[] preparazioneMateriale = new int[] {7, 5};

    abstract boolean inizia(int t) throws InterruptedException;

    abstract void termina(int t) throws InterruptedException;

    void log(int t, String msg) {
        String tipo = t == 0 ? "cemento" : "mattoni";
        String finalMsg = String.format("%d (%s): %s", Thread.currentThread().getId(), tipo, msg);
        System.out.println(finalMsg);
    }

    void attesa(int t) {
        try {
            TimeUnit.SECONDS.sleep(t);
        } catch (InterruptedException e) { e.printStackTrace(); }
    }

    void test(int cemento, int mattoni) {
        for (int i = 0; i < cemento; i++) {
            new Muratore(this, 0).start();
        }
        for (int i = 0; i < mattoni; i++) {
            new Muratore(this, 1).start();
        }
    }
}
