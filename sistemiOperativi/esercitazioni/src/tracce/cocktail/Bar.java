package tracce.cocktail;

import java.util.concurrent.TimeUnit;

public abstract class Bar {

    final protected int N;
    final protected int TIPI_COCKTAIL = 2;
    protected int[] tempoPreparazione = new int[TIPI_COCKTAIL];
    protected int[] prezzo = new int[TIPI_COCKTAIL];

    protected Bar(int n) {
        N = n;
    }

    void prepara(int tipo) {
        try {
            TimeUnit.SECONDS.sleep(tempoPreparazione[tipo]);
        } catch (InterruptedException e) { e.printStackTrace(); }
    }

    abstract void ordinaCocktail(int tipo) throws InterruptedException;

    abstract void preparaCocktail() throws InterruptedException;

    abstract void beviEpaga(int tipo) throws InterruptedException;

    abstract void chiudiBar() throws InterruptedException;

    void test() {
        Barman b = new Barman(this);
        b.start();
        for (int i = 0; i < this.N; i++) {
            new Cliente(this).start();
        }
    }
}
