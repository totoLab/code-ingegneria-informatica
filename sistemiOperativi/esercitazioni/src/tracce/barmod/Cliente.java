package tracce.barmod;

public class Cliente extends Thread {

    Bar bar;

    Cliente(Bar bar) {
        this.bar = bar;
    }

    @Override
    public void run() {
        try {
            int i = bar.scegli();
            bar.inizia(i);
            bar.finisci(i);
            bar.inizia(1 - i);
            bar.finisci(1 - i);

        } catch (InterruptedException e) { e.printStackTrace(); }
    }
}
