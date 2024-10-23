package tracce.aziendaagricola;

public class Magazziniere extends Thread {

    private AziendaAgricola azienda;

    public Magazziniere(AziendaAgricola azienda) {
        this.azienda = azienda;
    }

    @Override
    public void run() {
        while (true) {
            azienda.restock();
        }
    }
}
