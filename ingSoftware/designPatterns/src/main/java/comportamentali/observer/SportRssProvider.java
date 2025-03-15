package comportamentali.observer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SportRssProvider extends RssProvider {

    List<Game> games = new ArrayList<>();

    public class Game {
        Date date;
        String club1;
        String club2;
        int score1;
        int score2;

        public Game(Date date, String club1, String club2, int score1, int score2) {
            this.date = date;
            this.club1 = club1;
            this.club2 = club2;
            this.score1 = score1;
            this.score2 = score2;
        }

        public Date getDate() {
            return date;
        }

        public int getScore1() {
            return score1;
        }

        public int getScore2() {
            return score2;
        }

        public String getClub1() {
            return club1;
        }

        public String getClub2() {
            return club2;
        }
    }

    public List<Entry> getData() {
        List<Entry> newEntries = new ArrayList<>();
        for (SportRssProvider.Game game : games) {
            Entry entry = new Entry(
                    String.format("%s vs %s", game.getClub1(), game.getClub2()),
                    String.format("On %s the game ended %s %d - %s %d.", game.getDate(), game.getClub1(), game.getScore1(), game.getClub2(), game.getScore2())
            );
            newEntries.add(entry);
        }
        return newEntries;
    }
}
