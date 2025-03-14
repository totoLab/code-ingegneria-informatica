package strutturali.proxy.virtual;

import java.nio.file.Path;

public class RealImage implements Image {

    private Path path;

    public RealImage(Path path) {
        this.path = path;
        load(path);
    }

    private void load(Path path) {
        System.out.println("Loading image from " + path);
        System.out.println("Image loaded");
    }

    @Override
    public void show() {
        System.out.println("Showing real image");
    }
}
