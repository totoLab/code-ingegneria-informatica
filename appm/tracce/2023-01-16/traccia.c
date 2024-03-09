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

void verifica_vet_matrice(
    int** matrix, int n, int m,
    int* V, int k, int* colKappa, int* rig100) {

    int i, j;
    for (i = 0; i < n; i++) {
        int sum = 0, counter = 0;
        for (j = 0; j < m; j++) {
            int el = matrix[i][j];
            if (el > 10) counter++;
            sum += el;
        }
        if (counter >= 3 && sum > 100) (*rig100)++;
    }

    for (j = 0; j < m; j++) {
        int counter = 0;
        for (i = 0; i < n; i++) {
            int el = matrix[i][j];
            if (el % 2 == 0) counter++;
        }
        if (counter > k) (*colKappa)++;
    }
}

int main() {
    int n, m;
    int** matrix = create(&n, &m);
    read(matrix, n, m);
    print(matrix, n, m);
    int* V; // vector unused in function
    int colKappa = 0, rig100 = 0;
    verifica_vet_matrice(matrix, n, m, V, 3, &colKappa, &rig100);
    printf("colKappa = %d, rig100 = %d", colKappa, rig100);
}