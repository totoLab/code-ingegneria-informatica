package es4.Stampe;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Stampa2_1 {

    static Semaphore semA = new Semaphore(1);
    static Semaphore semB = new Semaphore(0);

    static Semaphore mutex = new Semaphore(1);
    static String last = "B";

    static class A extends Thread {

        @Override
        public void run() {
            try {
                semA.acquire();
                System.out.print("A");
                mutex.acquire();
                if (last.equals("A")) {
                    semB.release();
                    last = "A";
                    mutex.release();
                    return;
                }
                last = "A";
                semA.release();
                mutex.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class B extends Thread {

        @Override
        public void run() {
            try {
                semB.acquire();
                System.out.print("B");
                mutex.acquire();
                if (last.equals("B")) {
                    semA.release();
                    last = "B";
                    mutex.release();
                    return;
                }
                last = "B";
                semB.release();
                mutex.release();
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

