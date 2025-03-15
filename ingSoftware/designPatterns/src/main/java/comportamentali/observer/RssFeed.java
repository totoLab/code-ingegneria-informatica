package comportamentali.observer;

public abstract class RssFeed {

    protected RssProvider rssProvider;

    public void setRssProvider(RssProvider provider) {
        this.rssProvider = provider;
    }

    abstract void update();
}
