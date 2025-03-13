package strutturali.bridge.operations.overclocking;

public class GpuOverclock extends AbstractOverclock {

    @Override
    public boolean overclock(String device) {
        System.out.println("GPU overclock is disabled, update your firmware");
        return false;
    }
}
