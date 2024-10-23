package tracce.visualizzatore;

import java.util.LinkedList;

public abstract class Coda {

    protected final LinkedList<String> coda = new LinkedList<>();
    protected final int capacity = 100;
    protected int size = 0;

    abstract void inserisci(int n) throws InterruptedException;

    abstract void preleva() throws InterruptedException;

    public void test(int n) {
        Visualizzatore v = new Visualizzatore(this);
        v.start();
        for (int i = 0; i < n; i++) {
            new Utente(this).start();
        }
    }
}
