#include <stdio.h>
#include <stdlib.h>

double** create(int n, int m);
void read(double** matrix, int n, int m);
void print(double** matrix, int n, int m);
void verifica_mat(
    double** matrix, int n, int m,
    int* V, int K, int* double_col, int* five_rows);

double** create(int n, int m) {
    double** ret = malloc(n * sizeof(int*));
    int i;
    for (i = 0; i < n; i++) {
        ret[i] = malloc(m * sizeof(double));
    }
    return ret;
}

void read(double** matrix, int n, int m) {
    int i, j;
    for (i = 0; i < n; i++) {
        for (j = 0; j < m; j++) {
            printf("Input cell (%d,%d): ", i, j);
            scanf("%lf", &matrix[i][j]);
        }
    }
}

void print(double** matrix, int n, int m) {
    int i, j;
    for (i = 0; i < n; i++) {
        printf("|");
        for (j = 0; j < m; j++) {
            if (j < m - 1) printf("%5lf, ", matrix[i][j]);
            else printf("%5lf|\n", matrix[i][j]);
        }
    }
}

void verifica_mat(
    double** matrix, int n, int m,
    int* V, int K, int* double_col, int* five_rows) {
    
    int i, j;
    for (i = 0; i < n; i++) {
        double sum = 0;
        for (j = 0; j < n; j++) {
            sum += matrix[i][j];
        }
        if (sum < 5 * sum / m) (*five_rows)++;
    } // \sum^k{v} < 5 * \sum^k{v / k}

    int k;
    for (k = 0; k < K; k++) {
        int counter = 0;
        for (j = 0; j < n; j++) {
            for (i = 0; i < n; i++) {
                if (V[k] == matrix[i][j]) counter++;
            }
        }
        if (counter >= 2) (*double_col)++;
    }
}

void main() {
    int n = 2, m = 2;
    double** matrix = create(n, m);
    read(matrix, n, m);
    print(matrix, n, m);

    int p = 2;
    int V_temp[] = {8, 5};
    int* V = malloc(p * sizeof(int));
    for (int i = 0; i < p; i++) {
        V[i] = V_temp[i];
    }
    V = V_temp;
    int double_col = 0, five_rows = 0;
    verifica_mat(matrix, n, m, V, p, &double_col, &five_rows);
    printf("double_col = %d, five_rows = %d\n", double_col, five_rows);
}
