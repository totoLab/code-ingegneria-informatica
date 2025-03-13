package strutturali.decorator;

public class ProgressDecorator extends DownloaderDecorator {

    public ProgressDecorator(Downloader downloader) {
        super(downloader);
    }

    @Override
    public String getUrl() {
        return downloader.getUrl();
    }

    @Override
    public void download() {
        System.out.println("Downloading progress 0%");
        downloader.download();
        System.out.println("Downloading progress 100%");
    }
}
