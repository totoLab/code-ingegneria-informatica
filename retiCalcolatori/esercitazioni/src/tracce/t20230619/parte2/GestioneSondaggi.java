package tracce.t20230619.parte2;

import java.net.Socket;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestioneSondaggi {

    static int ID_SONDAGGI = 0;

    HashMap<Integer, SondaggioCompleto> sondaggi = new HashMap<>();

    class SondaggioCompleto {
        Socket azienda;
        Sondaggio sondaggio;
        LocalTime time;
        List<HashMap<Boolean, Integer>> risposte;

        SondaggioCompleto(Socket azienda, Sondaggio sondaggio) {
            this.azienda = azienda;
            this.sondaggio = sondaggio;
            time = LocalTime.now();
            risposte = new ArrayList<>();
        }

        public Socket getAzienda() {
            return azienda;
        }

        boolean terminato() {
            return time.plusHours(1).isAfter(LocalTime.now());
        }
    }

    synchronized int addSondaggio(Socket azienda, Sondaggio sondaggio) {
        if (sondaggio.getId() != null) return -1;

        sondaggio.setId(ID_SONDAGGI);
        SondaggioCompleto s = new SondaggioCompleto(azienda, sondaggio);
        sondaggi.put(ID_SONDAGGI, s);

        ID_SONDAGGI++;
        return s.sondaggio.getId();
    }

    synchronized SondaggioCompleto getSondaggio(int id) {
        return sondaggi.get(id);
    }

    synchronized SondaggioCompleto getSondaggio(Sondaggio sondaggio) {
        if (sondaggio.getId() == null || !sondaggi.containsKey(sondaggio.getId())) throw new IllegalArgumentException("Sondaggio non registrato");
        return sondaggi.get(sondaggio.getId());
    }

    synchronized boolean addRisposte(Sondaggio s, RispostaSondaggio risposta) {
        SondaggioCompleto sondaggio = getSondaggio(s);
        if (sondaggio.terminato()) return false;
        if (sondaggio.sondaggio.getDomande().size() != risposta.getRisposte().size()) throw new IllegalArgumentException("Different sizes");

        int si = 0;
        int no = 0;
        for (String r : risposta.getRisposte()) {
            if (r.toUpperCase().equals("SI")) si++;
            else if (r.toLowerCase().equals("NO")) no++;
        }

        for (HashMap<Boolean, Integer> map : sondaggio.risposte) {
            map.put(true, map.get(true) + si);
            map.put(false, map.get(false) + no);
        }

        return true;
    }

    Map<String, HashMap<Boolean, Integer>> getRisposte(SondaggioCompleto sondaggioCompleto) {
        Map<String, HashMap<Boolean, Integer>> ret = new HashMap<>();
        List<String> domande = sondaggioCompleto.sondaggio.getDomande();
        for (int i = 0; i < domande.size(); i++) {
            String domanda = domande.get(i);
            ret.put(domanda, sondaggioCompleto.risposte.get(i));
        }
        return ret;
    }
}
