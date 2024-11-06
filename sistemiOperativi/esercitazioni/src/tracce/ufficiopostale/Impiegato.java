package tracce.ufficiopostale;

public class Impiegato extends Thread {

    UfficioPostale ufficio;
    int tipo;

    Impiegato(UfficioPostale ufficio, int tipo) {
        this.ufficio = ufficio;
        this.tipo = tipo;
    }

    @Override
    public void run() {
        while (true) {
            try {
                ufficio.prossimoCliente();
                ufficio.eseguiOperazione();
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}
