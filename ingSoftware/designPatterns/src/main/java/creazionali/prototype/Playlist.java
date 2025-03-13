package creazionali.prototype;

import java.util.ArrayList;
import java.util.List;

public class Playlist implements Prototype {

    List<Song> songs;

    public Playlist(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public Prototype clone() {
        return new Playlist(new ArrayList<>(songs));
    }
}
