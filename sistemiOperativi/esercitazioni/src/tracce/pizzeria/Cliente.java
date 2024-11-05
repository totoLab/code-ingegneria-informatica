package tracce.pizzeria;

public class Cliente extends Thread {

    Pizzeria pizzeria;

    public Cliente(Pizzeria pizzeria) {
        this.pizzeria = pizzeria;
    }

    @Override
    public void run() {
        try {
            pizzeria.entra();
            pizzeria.mangiaPizza();
        } catch (InterruptedException e) { e.printStackTrace(); }
    }
}
