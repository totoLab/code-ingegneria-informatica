package strutturali.bridge.operations.overclocking;

public class CpuOverclock extends AbstractOverclock {

    @Override
    public boolean overclock(String device) {
        return os.overclock("cpu");
    }
}
