package es4.Stampe;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Stampa9 {

    static Semaphore mutexA = new Semaphore(6);
    static Semaphore mutexB = new Semaphore(0);
    static Semaphore mutexC = new Semaphore(0);

    static boolean BoC = false;

    static class A extends Thread {

        @Override
        public void run() {
            try {
                mutexA.acquire(6);
                System.out.print("A");
                BoC = !BoC;
                if (BoC) {
                    mutexB.release(6);
                } else {
                    mutexC.release(6);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class B extends Thread {

        @Override
        public void run() {
            try {
                mutexB.acquire();
                System.out.print("B");
                mutexA.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class C extends Thread {

        @Override
        public void run() {
            try {
                mutexC.acquire();
                System.out.print("C");
                mutexA.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        while (true) {
            new A().start();
            new B().start();
            new C().start();
            TimeUnit.SECONDS.sleep(1);
        }
    }
}

