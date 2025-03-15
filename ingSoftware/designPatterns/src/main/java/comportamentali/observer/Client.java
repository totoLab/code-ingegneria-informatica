package comportamentali.observer;

public class Client {

    public static void main(String[] args) {

        RssFeed feed = new DesktopRssFeed();

        RssProvider provider = new SportRssProvider();
        provider.attach(feed);

        provider.sendNotification();
    }
}
