package comportamentali.observer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EconomicsRssProvider extends RssProvider {

    List<Article> articles = new ArrayList<>();

    public class Article {
        Date date;
        String title;
        String text;

        public Article(Date date, String title, String text) {
            this.date = date;
            this.title = title;
            this.text = text;
        }

        public Date getDate() {
            return date;
        }

        public String getText() {
            return text;
        }

        public String getTitle() {
            return title;
        }
    }

    public List<Entry> getData() {
        List<Entry> newEntries = new ArrayList<>();
        for (EconomicsRssProvider.Article article : articles) {
            Entry entry = new Entry(
                    article.getTitle(),
                    article.getText()
            );
            newEntries.add(entry);
        }
        return newEntries;
    }
}
