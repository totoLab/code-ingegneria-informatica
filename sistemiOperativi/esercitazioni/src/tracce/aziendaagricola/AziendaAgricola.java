package tracce.aziendaagricola;

public abstract class AziendaAgricola {
    int sacchiMax = 5;
    int sacchiRimasti = sacchiMax;

    abstract void paga(int id, int i);

    abstract void prendiSacchi(int id, int n);

    abstract void restock();

    public void test(int numeroClienti) {
        Magazziniere magazziniere = new Magazziniere(this);
        magazziniere.start();
        for (int i = 0; i < numeroClienti; i++) {
            new Cliente(i, this).start();
        }
    }

}
