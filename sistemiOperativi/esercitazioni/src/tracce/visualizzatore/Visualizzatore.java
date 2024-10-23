package tracce.visualizzatore;

public class Visualizzatore extends Thread {

    Coda coda;

    Visualizzatore(Coda coda) {
        this.coda = coda;
    }

    @Override
    public void run() {
        while (true) {
            try {
                coda.preleva();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
