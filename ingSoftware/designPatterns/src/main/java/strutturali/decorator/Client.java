package strutturali.decorator;

public class Client {

    public static void main(String[] args) {
        Downloader downloader = new MyDownloader("google.com");
        downloader.download();

        System.out.println();

        Downloader progress = new ProgressDecorator(downloader);
        progress.download();

        System.out.println();

        Downloader cache = new CacheDecorator(progress);
        cache.download();

        System.out.println();

        Downloader complete =
                new CacheDecorator(
                new ProgressDecorator(
                new MyDownloader("facebook.com")
        ));
        complete.download();
    }
}
