package es4.es4_3;

public class MatrixNTS extends Matrix {

    MatrixNTS(int n, int m) {
        super(n, m);
    }

    @Override
    void update(int i, int j, int value) {
        matrix[i][j] += value;
    }

    public static void main(String[] args) {
        Matrix matrix = new MatrixNTS(12, 12);
        for (int i = 0; i < 100000; i++) {
            matrix.test();
        }
    }
}
