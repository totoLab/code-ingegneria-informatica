package es4.es4_3;

public class ColIncrementer extends Thread {

    Matrix matrix;
    int index;
    int repetitions;

    public ColIncrementer(Matrix matrix, int index, int repetitions) {
        this.matrix = matrix;
        this.index = index;
        this.repetitions = repetitions;
    }

    @Override
    public void run() {
        for (int i = 0; i < repetitions; i++) {
            for (int j = 0; j < matrix.m; j++) {
                matrix.update(j, index, 1);
            }
        }
    }
}
