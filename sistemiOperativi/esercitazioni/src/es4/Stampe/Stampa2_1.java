package es4.Stampe;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Stampa2_1 {

    static Semaphore semA = new Semaphore(2);
    static Semaphore semB = new Semaphore(0);
    static boolean AoB = false;

    static class A extends Thread {

        @Override
        public void run() {
            try {
                semA.acquire(2);
                System.out.print("A");
                AoB = !AoB;
                if (AoB) {
                    semA.release(2);
                } else {
                    semB.release(2);
                }
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
                semA.release();
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

