package tracce.catenamontaggio;

import tracce.binariferroviari.CantiereFerroviario;

public class Assemblatore extends Thread {

    CatenaDiMontaggioA catenaDiMontaggio;
    int[][] richieste;

    Assemblatore(CatenaDiMontaggioA catenaDiMontaggio, int[][] richieste) {
        this.catenaDiMontaggio = catenaDiMontaggio;
        this.richieste = richieste;
    }

    @Override
    public void run() {
        for (int[] richiesta : richieste) {
            try {
                int sx = richiesta[0];
                int dx = richiesta[1];
                catenaDiMontaggio.richiediProduzione(sx, dx);
                catenaDiMontaggio.assembla();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
