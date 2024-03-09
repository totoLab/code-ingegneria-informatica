#include <stdio.h>
#include <stdlib.h>
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
            printf("Input value for cell (%d,%d): ", i, j);
            scanf("%d", &matrix[i][j]);
        }
    }
}

void print(int** matrix, int n, int m) {
    int i, j;
    for (i = 0; i < n; i++) {
        printf("|");
        for (j = 0; j < m; j++) {
            if (j < m - 1) {  
                printf("%5d, ", matrix[i][j]);
            } else {
                printf("%5d|\n", matrix[i][j]);
            }
        }
    }
}

void piccolo_vett(int** matrix, int n, int m, int* V, int* rows, int* cols) { // len(V) = m
    int i, j;
    for (i = 0; i < n; i++) {
        int ok = 0;
        for (j = 0; j < m; j++) {
            int el = matrix[i][j];

            if (el <= 3 * V[j]) ok = 1;
        }
        if (ok) (*rows)++;
    }

    for (j = 0; j < m; j++) {
        int sum_1 = 0, sum_2 = 0;
        int middle = n / 2;
        for (i = 0; i < n; i++) {
            int el = matrix[i][j];

            if (i <= middle) sum_1 += el;
            else sum_2 += el;
        }
        if (sum_1 > sum_2) (*cols)++;
    }
}

int main() {
    int n = 2, m = 2;
    int** matrix = create(n, m);
    read(matrix, n, m);
    print(matrix, n, m);

    int V_temp[] = {2, 1};
    int* V = calloc(m, sizeof(int));
    for (int i = 0; i < m; i++) {
        V[i] = V_temp[i];
    }

    int rows = 0, cols = 0;
    piccolo_vett(matrix, n, m, V, &rows, &cols);
    printf("rows = %d, cols = %d\n", rows, cols);
}