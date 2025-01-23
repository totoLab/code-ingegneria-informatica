package tracce.t20210210.parte2;

import java.util.concurrent.atomic.AtomicInteger;

public class Agente extends Thread {

    public static AtomicInteger ID_PROGRESSIVO = new AtomicInteger(0);
    int id;

    public Agente() {
        this.id = ID_PROGRESSIVO.getAndAdd(1);
    }
}
