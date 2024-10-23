package es4.es4_1;

import java.util.concurrent.Semaphore;

public class MutexSync {

    static class T extends Thread {

        String message;

        T(String name, String message) {
            super(name);
            this.message = message;
        }

        @Override
        public void run() {
            System.out.println(message);
        }
    }

    static class Mutex extends T {

        // static keyword makes semaphore shared between the two instances (output is the same without it, but logic is different)
        static Semaphore mutex = new Semaphore(1);

        Mutex(String name, String message) {
            super(name, message);
        }

        @Override
        public void run() {
            try {
                mutex.acquire();
                super.run();
                mutex.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Sync extends T {

        // static keyword makes semaphore shared between the two instances (can't work without it because thread p2 would never benefit from the release of p1)
        static Semaphore mutex = new Semaphore(0);

        Sync(String name, String message) {
            super(name, message);
        }

        @Override
        public void run() {
            if (getName().equals("p2")) {
                try {
                    mutex.acquire();
                    super.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (getName().equals("p1")) {
                super.run();
                mutex.release();
            } else {
                System.err.println("something went wrong");
            }
        }
    }

    static T factory(String name, String message) {
        return new Sync(name, message);
    }

    public static void main(String[] args) {
        T t1 = factory("p1", "A");
        T t2 = factory("p2", "B");
        t1.start();
        t2.start();
    }
}

