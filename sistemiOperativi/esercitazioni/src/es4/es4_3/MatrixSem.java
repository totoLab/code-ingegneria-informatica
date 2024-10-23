package es4.es4_3;

import java.util.concurrent.Semaphore;

public class MatrixSem extends Matrix {

    Semaphore mutex = new Semaphore(1);

    public MatrixSem(int n, int m) {
        super(n, m);
    }

    @Override
    void update(int i, int j, int value) {
        try {
            mutex.acquire();
            matrix[i][j] += value;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mutex.release();
        }
    }

    public static void main(String[] args) {
        Matrix matrix = new MatrixSem(3, 3);
        matrix.test();
    }
}
