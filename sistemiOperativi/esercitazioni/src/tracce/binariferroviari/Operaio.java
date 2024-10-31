package tracce.binariferroviari;

import java.util.concurrent.TimeUnit;

public class Operaio extends Thread {
    private int tipo; // 0 = traversa, 1 = rotaia
    private CantiereFerroviario cantiere;
    private int id;

    public Operaio(int tipo, CantiereFerroviario cantiere, int id) {
        this.tipo = tipo;
        this.cantiere = cantiere;
        this.id = id;
    }

    private void attesa(int t) {
        try {
            TimeUnit.SECONDS.sleep(t);
        } catch (InterruptedException e) { e.printStackTrace();}
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Preparazione del materiale
                if (tipo == 0) {
                    System.out.println("Operaio traverse " + id + " prepara materiale (20 minuti)");
                    attesa(20); // 20 minuti per preparare la traversa
                } else {
                    System.out.println("Operaio rotaie " + id + " prepara materiale (30 minuti)");
                    attesa(30); // 30 minuti per preparare la rotaia
                }

                // Lavoro sul binario
                cantiere.lavora(tipo);

                // Segnala il termine dell'attività
                cantiere.termina(tipo);

                // Riposo di 10 minuti
                System.out.println("Operaio " + (tipo == 0 ? "traverse" : "rotaie") + " " + id + " si riposa (10 minuti)");
                attesa(10);
            }
        } catch (InterruptedException e) {
            System.out.println("Operaio " + (tipo == 0 ? "traverse" : "rotaie") + " " + id + " interrotto.");
        }
    }
}
