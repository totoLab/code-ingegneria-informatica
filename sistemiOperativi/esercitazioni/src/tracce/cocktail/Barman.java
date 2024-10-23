package tracce.cocktail;

public class Barman extends Thread {

    private Bar bar;

    Barman(Bar bar) {
        this.bar = bar;
    }

    @Override
    public void run() {
        try {
            bar.preparaCocktail();
            bar.chiudiBar();
        } catch (InterruptedException e) { e.printStackTrace(); }
    }
}
