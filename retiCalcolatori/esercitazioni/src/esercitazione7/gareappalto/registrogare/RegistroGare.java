package esercitazione7.gareappalto.registrogare;

import java.net.*;
import java.util.*;

public class RegistroGare {

    static int COUNTER = 0;

    HashMap<Integer, Gara> registro = new HashMap<>();

    public synchronized int addGara(Socket ente, Richiesta richiesta) {
        registro.put(COUNTER++, new Gara(ente, richiesta));
        return COUNTER;
    }

    /**
     * @return true if Gara object is accepting offers, else otherwise. It's not correlated to offer insertion
     */
    public synchronized boolean addOfferta(Offerta offerta) {
        return registro.get(offerta.getIdGara()).addOfferta(offerta);
    }

    public Richiesta getRichiesta(int idGara) {
        return registro.get(idGara).getRichiesta();
    }

    public Socket getEnte(int idGara) {
        return registro.get(idGara).getEnte();
    }

    public boolean isActive(int idGara) {
        return registro.get(idGara).isActive();
    }

    public void closeGara(int idGara) {
        registro.get(idGara).setStatus(false);
    }

    public Offerta getOffertaMigliore(int idGara) {
        return registro.get(idGara).getOffertaMigliore();
    }

}
