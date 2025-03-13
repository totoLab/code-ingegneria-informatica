package strutturali.bridge.os;

import java.nio.file.Path;

public class Windows extends AbstractOS {
    @Override
    public int createProcess() {
        System.out.println("Using CreateProcessA function");
        return 0;
    }

    @Override
    public boolean createFile(Path path) {
        System.out.println("Using CreateFileA function");
        return true;
    }

    @Override
    public boolean overclock(String device) {
        System.out.println("Overclocking " + device);
        return true;
    }

    @Override
    public void reset(String device) {
        System.out.println("Reseting " + device);
    }
}
