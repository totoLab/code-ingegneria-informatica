package es5.barbiere;

import java.util.concurrent.TimeUnit;

public abstract class Sala {

    protected final int numSedie;
    protected int sedieLibere;

    public Sala(int sedie){
        numSedie = sedie; sedieLibere = sedie;
    }

    public abstract void tagliaCapelli() throws InterruptedException;

    public abstract boolean attendiTaglio() throws InterruptedException;

    public void test(int numClienti) throws InterruptedException{
        Barbiere b = new Barbiere(this);
        b.setDaemon(true);
        b.start();
        Cliente[] clienti = new Cliente[numClienti];
        for(int i = 0; i < numClienti; i++){
            clienti[i] = new Cliente(this, i);
            clienti[i].start();
            TimeUnit.SECONDS.sleep(2);
        }
    }
}