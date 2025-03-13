package creazionali.prototype;

import java.util.ArrayList;
import java.util.List;

public class Client {

    public static void main(String[] args) {
        List<Song> songs = new ArrayList<>();
        songs.add(new Song("La donna cannone", 234));
        songs.add(new Song("Praise", 341));
        songs.add(new Song("Il cielo in una stanza", 293));

        Playlist playlist = new Playlist(songs);
        Playlist copy = new Playlist(songs);
        check(playlist, copy);

        Playlist clone = (Playlist) playlist.clone();
        check(playlist, clone);

    }

    static void check(Playlist playlist, Playlist copy) {
        if (!playlist.songs.equals(copy.songs)) {
            System.out.println("Not copied correctly");
        } else {
            System.out.println(
                    playlist.songs == copy.songs
                            ? "Shallow copy"
                            : "Deep copy"
            );
        }
    }
}
