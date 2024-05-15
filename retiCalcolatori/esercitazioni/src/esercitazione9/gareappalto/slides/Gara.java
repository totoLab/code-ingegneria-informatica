package esercitazione9.gareappalto.slides;

import java.net.Socket;

public class Gara {

    private int id;
    Socket ente;
    Richiesta richiesta;
    Offerta offertaMigliore;
    boolean status;

    Gara(Socket ente, esercitazione9.gareappalto.slides.Richiesta richiesta) {
        this.ente = ente;
        this.richiesta = richiesta;
        this.status = true;
    }

    public int getId() {
        return id;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isActive() {
        return status;
    }

    public void setOffertaMigliore(Offerta offertaMigliore) {
        this.offertaMigliore = offertaMigliore;
    }
}
