package strutturali.proxy.virtual;

import java.nio.file.Path;

public class Client {

    public static void main(String[] args) {
        Image image = new VirtualImage(Path.of("pic.png"));
        image.show();

        image.show();
    }
}
