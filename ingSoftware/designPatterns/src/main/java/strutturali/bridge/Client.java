package strutturali.bridge;

import strutturali.bridge.operations.creation.CreateFile;
import strutturali.bridge.operations.creation.CreateProcess;
import strutturali.bridge.operations.creation.Creator;
import strutturali.bridge.operations.overclocking.CpuOverclock;
import strutturali.bridge.operations.overclocking.GpuOverclock;
import strutturali.bridge.operations.overclocking.Overclock;
import strutturali.bridge.os.AbstractOS;
import strutturali.bridge.os.Linux;
import strutturali.bridge.os.Windows;

import java.nio.file.Path;

public class Client {

    public static void main(String[] args) {

        AbstractOS os = new Windows();

        Creator operation = new CreateProcess();
        operation.setArchitecture(os);
        System.out.println(operation.getDescription());
        System.out.println("Return code " + operation.run());

        operation = new CreateFile(Path.of("~/Downloads/test.txt"));
        operation.setArchitecture(os);
        System.out.println(operation.getDescription());
        System.out.println("Return code " + operation.run());

        Overclock overclocker = new CpuOverclock();
        overclocker.setArchitecture(os);
        overclocker.overclock(null);
        overclocker.reset();

        overclocker = new GpuOverclock();
        overclocker.setArchitecture(os);
        overclocker.overclock(null);
    }
}
