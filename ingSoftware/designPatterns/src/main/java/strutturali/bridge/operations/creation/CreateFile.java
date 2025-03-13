package strutturali.bridge.operations.creation;

import strutturali.bridge.os.AbstractOS;

import java.nio.file.Path;

public class CreateFile implements Creator {

    private AbstractOS os;
    private Path path;

    public CreateFile(Path path) {
        this.path = path;
    }

    @Override
    public void setArchitecture(AbstractOS architecture) {
        this.os = architecture;
    }

    @Override
    public String getDescription() {
        return "Creates a file at " + path;
    }

    @Override
    public int run() {
        return os.createFile(path) ? 0 : 1;
    }
}
