package strutturali.proxy.virtual;

import java.nio.file.Path;

public class VirtualImage implements Image {

    private final Path path;
    private RealImage realImage;

    public VirtualImage(Path path) {
        this.path = path;
    }

    @Override
    public void show() {
        if (realImage == null) {
            this.realImage = new RealImage(path);
        }
        realImage.show();
    }
}
