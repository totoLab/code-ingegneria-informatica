package strutturali.decorator;

import java.util.*;

public class CacheDecorator extends DownloaderDecorator {

    Map<String, String> cache = new HashMap<>();

    public CacheDecorator(Downloader downloader) {
        super(downloader);
    }

    @Override
    public String getUrl() {
        return downloader.getUrl();
    }

    @Override
    public void download() {
        String url = downloader.getUrl();
        if (cache.containsKey(url)) {
            System.out.println("Cache hit");
        } else {
            System.out.println("Cache miss");
            downloader.download();
            cache.put(url, "cached-content");
        }
    }
}
