package strutturali.decorator;

public class MyDownloader implements Downloader {

    private String url;

    public MyDownloader(String url) {
        this.url = url;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void download() {
        System.out.println("Started download for " + url);
    }
}
