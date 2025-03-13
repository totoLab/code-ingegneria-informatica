package strutturali.decorator;

public abstract class DownloaderDecorator implements Downloader {

    protected Downloader downloader;

    public DownloaderDecorator(Downloader downloader) {
        this.downloader = downloader;
    }

    abstract public String getUrl();

    @Override
    public void download() {
        downloader.download();
    }
}
