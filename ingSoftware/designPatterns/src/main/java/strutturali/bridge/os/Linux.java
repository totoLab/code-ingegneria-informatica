package strutturali.bridge.os;

import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

public class Linux extends AbstractOS {

    @Override
    public int createProcess() {
        System.out.println("Created a new process");
        return 0;
    }

    @Override
    public boolean createFile(Path path) {
        System.out.println("Created a new file");
        return true;
    }

    @Override
    public boolean overclock(String device) {
        System.out.printf("Starting overclock on %s by factor %d\n", device, this.overclockFactor);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            return false;
        }
        return true;
    }

    @Override
    public void reset(String device) {
        System.out.println("Reset overclock to stock");
    }
}
