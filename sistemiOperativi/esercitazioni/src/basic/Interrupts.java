package basic;

import java.util.Scanner;

public class Interrupts {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Cronometro cronometro = new Cronometro();
        System.out.print("Press enter to start: ");
        in.nextLine();
        cronometro.start();
        System.out.print("Press enter to stop: ");
        in.nextLine();
        cronometro.interrupt();
        System.out.println("Stopped.");
    }
}

class Cronometro extends Thread {

    int numSecondi = 1;

    public void run() {
        while (!isInterrupted()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
            System.out.print("\r" + numSecondi + "s. Press enter to stop: ");
            numSecondi++;
        }
    }
}