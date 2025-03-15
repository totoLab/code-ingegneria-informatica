package comportamentali.observer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DesktopRssFeed extends RssFeed {

    final private int maxEntries = 10;
    List<Entry> entries = new LinkedList<>();


    @Override
    public void update() {
        for (Entry entry : rssProvider.getData()) {
            addOne(entry);
        }
    }

    void addOne(Entry entry) {
        if (entries.size() >= maxEntries) {
            entries.removeFirst();
        }
        entries.add(entry);
    }
}
