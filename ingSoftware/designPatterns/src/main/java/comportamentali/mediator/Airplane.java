package comportamentali.mediator;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Airplane extends Colleague implements Runnable {

    private static int ID;
    private int id;

    public Airplane(Mediator mediator) {
        super(mediator);
        this.id = ++ID;
    }

    public boolean landingRequest() {
        notify("Requesting landing...");
        return mediator.landingRequest();
    }

    private void land() {
        notify("Landing now");
        ((ControlTower) mediator).landed();
    }

    @Override
    public void run() {
        Random random = new Random();
        wait(random.nextInt(3, 20));
        while (!landingRequest()) {
            notify("Request rejected, not allowed to land now");
            wait(4);
        }
        wait(random.nextInt(4, 7));
        land();

    }

    void wait(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {}
    }

    void notify(String message) {
        System.out.println(id + ": " + message);
    }
}
