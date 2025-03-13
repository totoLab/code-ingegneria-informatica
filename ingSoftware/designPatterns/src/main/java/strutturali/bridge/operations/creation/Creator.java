package strutturali.bridge.operations.creation;

import strutturali.bridge.os.AbstractOS;

public interface Creator {

    void setArchitecture(AbstractOS architecture);
    String getDescription();
    int run();
}
