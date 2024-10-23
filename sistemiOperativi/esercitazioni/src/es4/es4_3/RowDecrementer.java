package es4.es4_3;

public class RowDecrementer extends Thread {

    Matrix matrix;
    int index;
    int repetitions;

    public RowDecrementer(Matrix matrix, int index, int repetitions) {
        this.matrix = matrix;
        this.index = index;
        this.repetitions = repetitions;
    }

    @Override
    public void run() {
        for (int i = 0; i < repetitions; i++) {
            for (int j = 0; j < matrix.n; j++) {
                matrix.update(index, j, -1);
            }
        }
    }
}
