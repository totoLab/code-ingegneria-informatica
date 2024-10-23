package es4.es4_3;

public abstract class Matrix {

    protected int[][] matrix;
    final public int n, m;

    Matrix(int n, int m) {
        this.n = n;
        this.m = m;
        if (n <= 0 || m <= 0) throw new IllegalArgumentException();
        this.matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    abstract void update(int i, int j, int value);

    void printMatrix() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void test() {
        //printMatrix();
        // System.out.println();

        RowDecrementer[] dec = new RowDecrementer[n];
        ColIncrementer[] inc = new ColIncrementer[m];
        int x = 5;
        for (int i = 0; i < n; i++) {
            dec[i] = new RowDecrementer(this, i, x);
            dec[i].start();
        }

        for (int i = 0; i < m; i++) {
            inc[i] = new ColIncrementer(this, i, x);
            inc[i].start();
        }

        try {
            for (int i = 0; i < n; i++) {
                dec[i].join();
            }

            for (int i = 0; i < m; i++) {
                inc[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!verify()) {
            printMatrix();
            System.out.println("ERROR");
        }
    }

    private boolean verify() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] != 0) return false;
            }
        }
        return true;
    }
}
