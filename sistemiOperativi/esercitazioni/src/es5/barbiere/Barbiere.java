package es5.barbiere;

import java.util.concurrent.TimeUnit;

public class Barbiere extends Thread {

    Sala s;

    Barbiere(Sala s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            s.tagliaCapelli();
            taglio();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void taglio() throws InterruptedException {
        System.out.println("Taglio in corso...");
        TimeUnit.SECONDS.sleep(3);
        System.out.println("Fine taglio");
    }

}
