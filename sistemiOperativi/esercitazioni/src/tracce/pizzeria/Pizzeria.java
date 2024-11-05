package tracce.pizzeria;

import tracce.palazzo.Squadra;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public abstract class Pizzeria {

    abstract void entra() throws InterruptedException;

    abstract void mangiaPizza() throws InterruptedException;

    abstract void preparaPizza() throws InterruptedException;

    abstract void serviPizza() throws InterruptedException;

    void attesa(int t) {
        try {
            TimeUnit.SECONDS.sleep(t);
        } catch (InterruptedException e) { e.printStackTrace(); }
    }

    void attesa(int t1, int t2) {
        try {
            Random random = new Random();
            TimeUnit.SECONDS.sleep(random.nextInt(t1, t2));
        } catch (InterruptedException e) { e.printStackTrace(); }
    }

    public static enum Tipo { PIZZAIOLO, CLIENTE }

    void log(String msg, Tipo tipo) {
        long id = Thread.currentThread().getId();
        String tipoString = String.format(
                tipo.equals(Tipo.PIZZAIOLO) ?
                        tipo.toString() : "%s %d", tipo.toString(), id
        );
        String finalMsg = String.format("%s: %s", tipoString, msg);
        System.out.println(finalMsg);
    }

    void test(int clienti) {
        Pizzaiolo pizzaiolo = new Pizzaiolo(this);
        pizzaiolo.start();
        for (int i = 0; i < clienti; i++) {
            new Cliente(this).start();
            attesa(2);
        }
    }
}
