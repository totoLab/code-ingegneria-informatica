package tracce.catenamontaggio;

public class Produttore extends Thread {

    CatenaDiMontaggioA catenaDiMontaggio;
    int tipo;
    Produttore(CatenaDiMontaggioA catenaDiMontaggio, int tipo) {
        this.catenaDiMontaggio = catenaDiMontaggio;
        this.tipo = tipo;
    }

    @Override
    public void run() {
        while (true) {
            try {
                catenaDiMontaggio.produci(tipo);
            } catch (InterruptedException e) {}
        }
    }
}
