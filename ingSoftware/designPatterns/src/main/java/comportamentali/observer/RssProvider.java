package comportamentali.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class RssProvider {

    private List<RssFeed> observers;

    public RssProvider() {
        this.observers = new ArrayList<>();
    }

    public void attach(RssFeed feed) {
        observers.add(feed);
        feed.setRssProvider(this);
    }

    public void detach(RssFeed feed) {
        observers.remove(feed);
    }

    public void sendNotification() {
        for (RssFeed feed : observers) {
            feed.update();
        }
    }

    abstract List<Entry> getData();
}
