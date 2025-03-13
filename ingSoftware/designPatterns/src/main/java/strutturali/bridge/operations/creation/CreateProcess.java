package strutturali.bridge.operations.creation;

import strutturali.bridge.os.AbstractOS;

public class CreateProcess implements Creator {

    AbstractOS os;

    @Override
    public void setArchitecture(AbstractOS architecture) {
        this.os = architecture;
    }

    @Override
    public String getDescription() {
        return "Creates a process";
    }

    @Override
    public int run() {
        return os.createProcess();
    }
}
