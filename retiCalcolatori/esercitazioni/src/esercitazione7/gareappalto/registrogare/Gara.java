package esercitazione7.gareappalto.registrogare;

import java.net.*;

public class Gara {

    final Richiesta richiesta;
    final Socket ente;
    boolean status;
    Offerta offertaMigliore;

    Gara(Socket ente, Richiesta richiesta) {
        this.ente = ente;
        this.richiesta = richiesta;
        this.status = true;
    }

    public Richiesta getRichiesta() {
        return richiesta;
    }

    public Socket getEnte() {
        return ente;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isActive() {
        return status;
    }

    boolean addOfferta(Offerta offerta) {
        if (!isActive()) return false;
        if (offertaMigliore == null) offertaMigliore = offerta;
        else if (offerta.getImportoRichiesto() < offertaMigliore.getImportoRichiesto()) {
            offertaMigliore = offerta;
        }
        return true;
    }

    public Offerta getOffertaMigliore() {
        return offertaMigliore;
    }


}
