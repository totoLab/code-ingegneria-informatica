package tracce.t20240624.parte1;

import java.util.*;

public class FilmService {

    HashSet<Film> films = new HashSet<>();
    ListRec listaRec = new ListRec();

    public FilmService() {
        listaRec.ListRecensione = new ArrayList<>();
    }

    class Recensione {
        String Utente;
        String Commento;
        int Punteggio;
    }

    class ListRec {
        List<Recensione> ListRecensione;
    }

    class Film {
        int id;
        String Titolo;
        String Genere;
        int Durata;
        String Autore;
        ListRec ListRecensioni;

        public Film(String titolo, String genere, int durata, String autore) {
            this.Titolo = titolo;
            this.Genere = genere;
            this.Durata = durata;
            this.Autore = autore;
        }
    }

    List<Film> CercaFilmAvanzata(String genere, int durata, int valutazione) {
        List<Film> ret = new ArrayList<>();
        for (Film film : films) {
            if (film.Genere.equals(genere) && film.Durata == durata) {
                int sommaValutazione = 0;
                for (Recensione recensione : listaRec.ListRecensione) {
                    sommaValutazione += recensione.Punteggio;
                }
                if (sommaValutazione / listaRec.ListRecensione.size() >= valutazione) {
                    ret.add(film);
                }
            }
        }
        return ret;
    }

    boolean AggiungiFilm(String titolo, String genere, int durata, String autore) {
        int contAutore = 0;
        int contGenere = 0;
        for (Film film : films) {
            if (film.Genere.equals(genere)) {
                contGenere += 1;
            }
            if (film.Autore.equals(autore)) {
                contAutore += 1;
            }
            if (contGenere >= 100 || contAutore >= 5) {
                return false;
            }
        }
        films.add(new Film(titolo, genere, durata, autore));
        return true;
    }
}
