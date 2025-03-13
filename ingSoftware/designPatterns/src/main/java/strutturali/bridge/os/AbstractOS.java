package strutturali.bridge.os;

import java.nio.file.Path;

public abstract class AbstractOS {

    protected int overclockFactor;

    public abstract int createProcess();
    public abstract boolean createFile(Path path);

    public abstract boolean overclock(String device);
    public abstract void reset(String device);
}
