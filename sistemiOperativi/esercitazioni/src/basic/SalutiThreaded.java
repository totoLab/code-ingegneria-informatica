package basic;

public class SalutiThreaded implements Runnable {

    final String name;

    SalutiThreaded(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.printf("Saluti da %s%n", this.name);
        }
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            Runnable runnable =  new SalutiThreaded(String.format("thread%d", i));
            threads[i] = new Thread(runnable);
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
    }
}
