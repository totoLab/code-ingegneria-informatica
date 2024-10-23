package tracce.cocktail;

import java.util.Random;

public class Cliente extends Thread {

    private Random random = new Random();
    private Bar bar;

    Cliente(Bar bar) {
        this.bar = bar;
    }

    @Override
    public void run() {
        try {
            int tipo = random.nextInt(0, 2);
            bar.ordinaCocktail(tipo);
            bar.beviEpaga(tipo);
        } catch (InterruptedException e) { e.printStackTrace(); }
    }
}
