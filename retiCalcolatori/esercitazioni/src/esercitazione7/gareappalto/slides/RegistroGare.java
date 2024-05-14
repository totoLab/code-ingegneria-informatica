package esercitazione7.gareappalto.slides;

import utils.Logging;

import java.net.Socket;
import java.util.HashMap;

import static utils.Logging.print;

public class RegistroGare {

    HashMap<Integer, Gara> gare = new HashMap<>();
    int counter = 0;

    public Richiesta getRichiesta(int idGara) {
        esercitazione7.gareappalto.slides.Richiesta richiesta = this.gare.get(idGara).richiesta;
        return richiesta;
    }
    public Offerta getOffertaVincente(int idGara) {
        return this.gare.get(idGara).offertaMigliore;
    }
    public Socket getSocketEnte(int idGara) {
        return this.gare.get(idGara).ente;
    }

    public synchronized int addGara(Gara gara) {
        gare.put(counter, gara);
        int id = counter++;
        return id;
    }

    public synchronized void chiudiGara(int idGara) {
        if (this.gare.containsKey(idGara)) {
            this.gare.get(idGara).setStatus(false);
        }
    }

    public synchronized void aggiungiOfferta(Offerta offerta) {
        int idGara = offerta.getId();
        if (this.gare.containsKey(idGara)) {
            Gara gara = this.gare.get(idGara);
            if (gara.isActive()) {
                Offerta oVincente = gara.offertaMigliore;
                if (
                        (
                                oVincente == null &&
                                offerta.getImportoRichiesto() <= gara.richiesta.getImportoMassimo()
                        ) || (
                                oVincente.getImportoRichiesto() > offerta.getImportoRichiesto()
                        ) || (
                                oVincente.getImportoRichiesto() == offerta.getImportoRichiesto() &&
                                offerta.getIdPartecipante() < oVincente.getIdPartecipante()
                        )
                ) {
                    gara.setOffertaMigliore(offerta);
                    print(Logging.Type.INFO, "Aggiunta offerta per gara " + idGara, null, null, null);
                }
            } else {
                print(Logging.Type.INFO, "* Rifiutata offerta fuori tempo per gara " + idGara, null, null, null);
            }
        }
    }

}
