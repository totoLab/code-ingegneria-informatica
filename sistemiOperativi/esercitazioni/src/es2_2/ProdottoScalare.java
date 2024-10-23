package es2_2;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
public class ProdottoScalare extends Thread{

    private int start;
    private int portion_length;
    private int product = 0;
    private int[] array1;
    private int[] array2;

    public int getProduct() {
        return product;
    }

    ProdottoScalare(int start, int portion_length, int[] array1, int[] array2) {
        this.start = start;
        this.portion_length = portion_length;
        this.array1 = array1;
        this.array2 = array2;
    }

    @Override
    public void run() {
        for (int i = start; i < start + portion_length; i++) {
            product += array1[i] * array2[i];
        }
        System.out.println(String.format("%s: %d", this.getName(), product));
    }

    private static void fill(int[] array) {
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(1, 4);
        }
    }

    public static void main(String[] args) {
        final int m = 2;
        final int n = m * 2;
        final int[] array1 = new int[n];
        fill(array1);
        System.out.println(Arrays.toString(array1));
        final int[] array2 = new int[n];
        fill(array2);
        System.out.println(Arrays.toString(array2));

        final int length = n / m;
        ProdottoScalare[] threads = new ProdottoScalare[m];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new ProdottoScalare(i * length, length, array1, array2);
            threads[i].start();
        }

        int total = 0;
        try {
            for (int i = 0; i < threads.length; i++) {
                threads[i].join();
                total += threads[i].getProduct();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Total: %s", total);
    }
}
