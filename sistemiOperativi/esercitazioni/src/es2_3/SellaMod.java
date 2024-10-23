package es2_3;

import java.util.*;

public class SellaMod {

    final int n, m;
    int[][] matrix;

    public SellaMod(int n, int m) {
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
        search();
    }

    static boolean search() {
        SellaMod sella = new SellaMod(4, 5);

        // rows
        SearchMod[] rows = new SearchMod[sella.n];
        for (int i = 0; i < sella.n; i++) {
            rows[i] = new SearchMod(sella.getMatrix(), i, true);
            rows[i].start();
        }

        // cols
        SearchMod[] cols = new SearchMod[sella.m];
        for (int i = 0; i < sella.m; i++) {
            cols[i] = new SearchMod(sella.getMatrix(), i, false);
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

        Set<Coordinates> s1 = new HashSet<>();
        Set<Coordinates> s2 = new HashSet<>();
        Set<Coordinates> intersection = new HashSet<>(s1);
        for (int i = 0; i < sella.n; i++) {
            Set<Coordinates> temp = new HashSet<>(rows[i].coordinates);
            s1.addAll(temp);
        }
        for (int i = 0; i < sella.m; i++) {
            Set<Coordinates> temp = new HashSet<>(cols[i].coordinates);
            s2.addAll(temp);
        }

        intersection.retainAll(s2);
        if (!intersection.isEmpty()) {
            for (Coordinates c : intersection) {
                System.out.printf("(%d, %d), ", c.x, c.y);
            }
        }
        return !intersection.isEmpty();
    }
}
class Coordinates {
    int x, y;
    Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
class SearchMod extends Thread {

    final int[][] matrix;
    final boolean rows;
    final int start;

    List<Coordinates> coordinates = new ArrayList<>();

    SearchMod(int[][] matrix, int start, boolean rows) {
        this.matrix = matrix;
        this.start = start;
        this.rows = rows;
    }

    @Override
    public void run() {
        int x, y;
        if (rows) {
            x = start;
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < matrix[0].length; i++) {
                int value = matrix[start][i];
                if (value > max) {
                    y = i;
                    max = value;
                    coordinates.add(new Coordinates(x, y));
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
                    coordinates.add(new Coordinates(x, y));
                }
            }
        }

    }
}