package basic;

public class Sync {
}

class Stampante extends Thread {

    private int start, end;

    Stampante(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            System.out.printf("%d ", i);
        }
        System.out.printf("%n");
    }

    public static void main(String[] args) throws InterruptedException {
        Stampante s1 = new Stampante(0, 10);
        Stampante s2 = new Stampante(10, 20);
        try {
            s1.start();
            s1.join();
            s2.start();
            s2.join();
            System.out.println("Fine");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}