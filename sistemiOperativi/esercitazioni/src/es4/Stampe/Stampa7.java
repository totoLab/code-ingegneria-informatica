package es4.Stampe;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Stampa7 {

    static Semaphore mutexA = new Semaphore(1);
    static Semaphore mutexB = new Semaphore(0);
    static Semaphore mutexC = new Semaphore(0);
    static int reps = 0;

    static class A extends Thread {

        @Override
        public void run() {
            try {
                mutexA.acquire();
                System.out.print("A");
                mutexB.release();
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
                reps++;
                if (reps == 2) {
                    reps = 0;
                    mutexC.release();
                } else {
                    mutexA.release();
                }
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
                System.out.print("C ");
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

