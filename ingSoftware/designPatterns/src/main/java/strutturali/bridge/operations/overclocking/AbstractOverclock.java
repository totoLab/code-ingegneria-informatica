package strutturali.bridge.operations.overclocking;

import strutturali.bridge.os.AbstractOS;

public abstract class AbstractOverclock implements Overclock {

    AbstractOS os;

    @Override
    public void setArchitecture(AbstractOS os) {
        this.os = os;
    }

    @Override
    public abstract boolean overclock(String device);

    @Override
    public void reset() {

    }
}
