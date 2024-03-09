#include <stdio.h>
#include <stdlib.h>
#include <float.h>

int** create(int n, int m) {
    int** matrix = malloc(n * sizeof(int*));
    int i;
    for (i = 0; i < n; i++) {
        matrix[i] = malloc(m * sizeof(int));
    }
    return matrix;
}

void read(int** matrix, int n, int m) {
    int i, j;
    for (i = 0; i < n; i++) {
        for (j = 0; j < m; j++) {
            printf("Insert value for cell (%d,%d): ", i, j);
            scanf("%d", &matrix[i][j]);
        }
    }
}

void print(int** matrix, int n, int m) {
    int i, j;
    for (i = 0; i < n; i++) {
        printf("|");
        for (j = 0; j < m; j++) {
            if (j < m - 1) printf("%5d, ", matrix[i][j]);
            else printf("%5d|\n", matrix[i][j]);
        }
    }
}

void processa_mat(int** matrix, int n, int m, int* V, int* rows, int* cols) {
    int i, j;
    for (i = 0; i < n; i++) {
        int ok = 0;
        for (j = 0; j < m; j++) {
            int el = matrix[i][j];
            if (el % 2 != 0 || el <= V[j]) ok = 1; 
        }
        if (ok) (*rows)++;
    }

    float media = 0.0f;
    for (j = 0; j < m; j++) {
        media += V[j];
    }
    media = media / m;

    for (j = 0; j < m; j++) {
        float max = FLT_MIN;
        for (i = 0; i < n; i++) {
            int el = matrix[i][j];
            if (el > max) max = el;
        }
        if (max > media) (*cols)++;
    }
}

int main() {
    int n = 2, m = 3;
    int** matrix = create(n, m);
    read(matrix, n, m);
    print(matrix, n, m);
    int* V = malloc(m * sizeof(int));
    for (int j = 0; j < m; j++) {
        printf("Insert j-th element of vector V: ");
        scanf("%d", &V[j]);
    }

    int rows = 0, cols = 0;
    processa_mat(matrix, n, m, V, &rows, &cols);
}