#include <stdio.h>
#include <stdlib.h>

int** create(int *n, int *m) {
    printf("Input number of rows: ");
    scanf("%d", n);
    printf("Input number of coloumns: ");
    scanf("%d", m);
    printf("Creating matrix %dx%d\n", *n, *m);
    int** mat;
    mat = malloc(*n * sizeof(int *));
    for (int i = 0; i < *n; i++) {
        mat[i] = malloc(*m * sizeof(int));
    }
    return mat;
}

void read(int** mat, int n, int m) {
    for (int i = 0; i < n; i++) {
        mat[i] = malloc(m * sizeof(int));
        for (int j = 0; j < m; j++) {
            printf("Input cell (%d,%d): ", i, j);
            scanf("%d", &mat[i][j]);
        }
    }
}

void print(int ** mat, int n, int m) {
    for (int i = 0; i < n; i++) {
        printf("|");
        for (int j = 0; j < m; j++) {
            printf("%5d", mat[i][j]);
            if (j + 1 < m) printf(", ");
        }
        printf("|\n");
    }
}

void somma_minima(
    int** matrix, int n, int m,
    int* value, int* r, int* c) {
    int i, j, k;
    for (i = 0; i < n; i++) {
        int sum_row = 0;
        for (j = 0; j < m; j++) {
            sum_row += matrix[i][j];
        }

        for (j = 0; j < m; j++) {
            int el = matrix[i][j];
            int elm = el * m;

            if (elm > sum_row) {
                
                int sum_col = 0;
                for (k = 0; k < n; k++) {
                    sum_col += matrix[k][j];
                }

                if (elm < sum_col) {
                    (*value) = el;
                    (*r) = i;
                    (*c) = j;
                    return;
                }
            }

        }
    }
}