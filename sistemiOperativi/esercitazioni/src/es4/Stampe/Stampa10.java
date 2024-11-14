package es4.Stampe;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Stampa10 {

    static Semaphore mutexA = new Semaphore(6);
    static Semaphore mutexB = new Semaphore(0);
    static Semaphore mutexC = new Semaphore(0);
    static Semaphore mutex = new Semaphore(1);

    static int[] attesa = new int[] { 0, 0 };

    static int pickIndex() throws InterruptedException {
        mutex.acquire();
        int index = 0;
        if (attesa[0] == attesa[1]) {
            Random random = new Random();
            index = random.nextInt(2);
        } else if (attesa[0] < attesa[1]) {
            index = 1;
        }
        mutex.release();
        return index;
    }

    static class A extends Thread {

        @Override
        public void run() {
            try {
                mutexA.acquire(6);
                System.out.print("A");
                if (pickIndex() == 0) {
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
                mutex.acquire();
                attesa[0]++;
                mutex.release();

                mutexB.acquire();
                System.out.print("B");

                mutex.acquire();
                attesa[0]--;
                mutex.release();

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
                mutex.acquire();
                attesa[1]++;
                mutex.release();

                mutexC.acquire();
                System.out.print("C");

                mutex.acquire();
                attesa[1]--;
                mutex.release();

                mutexA.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        while (true) {
            new A().start();
            for (int i = 0; i < random.nextInt(0, 3); i++) {
                new B().start();
            }
            for (int i = 0; i < random.nextInt(0, 3); i++) {
                new C().start();
            }
            TimeUnit.SECONDS.sleep(1);
        }
    }
}

