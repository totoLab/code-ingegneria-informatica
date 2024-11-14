package es4.Stampe;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Stampa8 {

    static int starting = 4;
    static int current = 0;
    static Semaphore mutexA = new Semaphore(1);
    static Semaphore mutexB = new Semaphore(0);

    static class A extends Thread {

        @Override
        public void run() {
            try {
                mutexA.acquire();
                System.out.print("A");
                current++;
                if (current == starting) {
                    mutexB.release();
                } else if (current < starting) {
                    mutexA.release();
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
                current = 0;
                starting--;
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
            TimeUnit.SECONDS.sleep(1);
        }
    }
}

