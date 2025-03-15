package comportamentali.state;

import comportamentali.state.states.Forno;
import comportamentali.state.states.FornoState;

public class Client {

    public static void main(String[] args) {
        Forno forno = new Forno();

        forno.apri();
        forno.chiudi();

        forno.start();

        // interrompi bruscamente
        forno.apri();
        forno.chiudi();

        // riprendi
        forno.start();

        // estendi
        forno.start();

        // interrompi normalmente
        forno.stop();

        // riparti
        forno.start();

        forno.apri();
    }
}
