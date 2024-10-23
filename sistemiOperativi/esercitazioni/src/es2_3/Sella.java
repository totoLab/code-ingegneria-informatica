package es2_3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Sella {

    final int n, m;
    int[][] matrix;

    public Sella(int n, int m) {
        this.n = n;
        this.m = m;
        matrix = new int[n][m];
        fill();
    }

    private void fill() {
        this.matrix = new int[][]{
                {2, 7, 2, 5, 2},
                {2, 5, 9, 7, 2},
                {1, 4, 1, 4, 1},
                {2, 5, 2, 9, 2}
        };


        /*Random rand = new Random();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = rand.nextInt(1, 100);
            }
        }*/
    }

    public int[][] getMatrix() {
        return matrix;
    }

    private void printMatrix() {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

    public static void main(String[] args) {
        boolean ok;
        int counter = 0;
        search();
    }

    static boolean search() {
        Sella sella = new Sella(4, 5);

        // rows
        Search[] rows = new Search[sella.n];
        for (int i = 0; i < sella.n; i++) {
            rows[i] = new Search(sella.getMatrix(), i, true);
            rows[i].start();
        }

        // cols
        Search[] cols = new Search[sella.m];
        for (int i = 0; i < sella.m; i++) {
            cols[i] = new Search(sella.getMatrix(), i, false);
            cols[i].start();
        }

        try {
            for (int i = 0; i < sella.n; i++) {
                rows[i].join();
            }
            for (int i = 0; i < sella.m; i++) {
                cols[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Store column minima in a map
        HashMap<Integer, Integer> columnMinima = new HashMap<>();
        for (int i = 0; i < sella.m; i++) {
            columnMinima.put(i, cols[i].x); // store the row index of the minimum element in each column
        }

        // Now verify the saddle points by checking only relevant elements
        for (int i = 0; i < sella.n; i++) {
            int colOfMax = rows[i].y; // Column of the maximum element in row i
            if (columnMinima.containsKey(colOfMax) && columnMinima.get(colOfMax) == i) {
                sella.printMatrix();
                System.out.printf("(%d, %d) found%n%n", i, colOfMax);
                return true;
            }
        }
        return false;
    }
}

class Search extends Thread {
    final int[][] matrix;
    final boolean rows;
    final int start;

    int x, y;

    Search(int[][] matrix, int start, boolean rows) {
        this.matrix = matrix;
        this.start = start;
        this.rows = rows;
    }

    @Override
    public void run() {
        if (rows) {
            x = start;
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < matrix[0].length; i++) {
                int value = matrix[start][i];
                if (value > max) {
                    y = i;
                    max = value;
                }
            }
        } else {
            y = start;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < matrix.length; i++) {
                int value = matrix[i][start];
                if (value < min) {
                    x = i;
                    min = value;
                }
            }
        }

    }
}