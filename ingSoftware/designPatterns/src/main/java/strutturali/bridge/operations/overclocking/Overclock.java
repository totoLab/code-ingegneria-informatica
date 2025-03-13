package strutturali.bridge.operations.overclocking;

import strutturali.bridge.os.AbstractOS;

public interface Overclock {

    void setArchitecture(AbstractOS os);
    boolean overclock(String device);
    void reset();
}
