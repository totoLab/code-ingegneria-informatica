package tracce.pizzeria;

public class Pizzaiolo extends Thread {

    Pizzeria pizzeria;

    public Pizzaiolo(Pizzeria pizzeria) {
        this.pizzeria = pizzeria;
    }

    @Override
    public void run() {
        while (true) {
            try {
                pizzeria.preparaPizza();
                pizzeria.serviPizza();
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}
