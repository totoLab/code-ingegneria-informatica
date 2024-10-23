package es7;

public class Es3 {

    public static void main(String[] args) {
        BW t1 = new BW(true);
        t1.start();
        Thread.State s0 = Thread.currentThread().getState();
        Thread.State s1 = t1.getState();
        System.out.printf("State 0: %s, State 1: %s%n", s0, s1);
        t1.bw = s0.equals(s1);
    }

    static class BW extends Thread {

        public boolean bw;
        public BW(boolean bw) {
            this.bw = bw;
        }

        @Override
        public void run() {
            do {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) { e.printStackTrace();}
            } while (bw);
            System.out.println("sisop-corsoB");
        }
    }
}
