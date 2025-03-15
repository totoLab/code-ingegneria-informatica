package comportamentali.mediator;

import java.util.concurrent.Semaphore;

public class ControlTower implements Mediator {

    private int landingRequests;
    private final int maxLandingRequests;
    private Semaphore mutex = new Semaphore(1);

    public ControlTower(int maxLandingRequests) {
        this.landingRequests = 0;
        this.maxLandingRequests = maxLandingRequests;
    }

    public void landed() {
    try {
        mutex.acquire();
    } catch (InterruptedException e) {}
    if (landingRequests > 0) landingRequests--;
    mutex.release();
    }

    @Override
    public boolean landingRequest() {
        try {
            mutex.acquire();
        } catch (InterruptedException e) {}
        if (landingRequests >= maxLandingRequests) {
            mutex.release();
            return false;
        }
        landingRequests++;
        mutex.release();
        return true;
    }
}
