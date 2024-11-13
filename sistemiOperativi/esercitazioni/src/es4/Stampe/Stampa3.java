package es4.Stampe;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Stampa3 {
    static Semaphore mutex = new Semaphore(0);

    static class A extends Thread {

        @Override
        public void run() {
            System.out.print("A");
            mutex.release();
        }
    }

    static class B extends Thread {

        @Override
        public void run() {
            try {
                mutex.acquire();
                System.out.print("B ");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        while (true) {
            new A().start();
            new B().start();
            TimeUnit.SECONDS.sleep(1);
        }
    }

}

