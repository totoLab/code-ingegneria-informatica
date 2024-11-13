package es4.es4_8;

import java.util.concurrent.Semaphore;

public class StampaOrdinata {

    public static void main(String[] args) {
        int n = 10;
        Semaphore[] semaphores = new Semaphore[n];
        for (int i = 0; i < n; i++) {
            semaphores[i] = new Semaphore(0);
        }

        new MyThread(0, new Semaphore(1), semaphores[0]).start();
        for (int i = 1; i < n; i++) {
            new MyThread(i, semaphores[i - 1], semaphores[i]).start();
        }

    }

    static class MyThread extends Thread {

        Semaphore wait;
        Semaphore next;
        int id;
        MyThread(int id, Semaphore s, Semaphore release) {
            this.id = id;
            this.wait = s;
            this.next = release;
        }

        @Override
        public void run() {
            try {
                wait.acquire();
                System.out.println("Thread" + id);
                next.release();
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}
