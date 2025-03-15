package comportamentali.observer;

public class Entry {
    private String title;
    private String text;

    public Entry(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}