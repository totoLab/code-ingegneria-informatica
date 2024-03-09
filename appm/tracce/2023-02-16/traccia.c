#include <stdint.h>
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
    int* V, int k, int* colVerifica, int* rigVerifica) {
    
    int i, j;
    int max = INT32_MIN;
    for (i = 0; i < n; i++) {
        for (j = 0; j < m; j++) {
            int el = matrix[i][j];
            if (el > max) max = el;
        }
    }

    for (i = 0; i < n; i++) {
        int sum = 0;
        for (j = 0; j < m; j++) {
            int el = matrix[i][j];
            if (el < 100) sum += el;
        }
        if (sum < max) (*rigVerifica)++;
    }

    int indexV;
    for (j = 0; j < m; j++) {
        int ok = 1;
        for (i = 0; i < n; i++) {
            if (ok || i % 2 != 0) {
                for (indexV = 0; indexV < k; indexV++) {
                    if (matrix[i][j] == V[indexV]) {
                        ok = 0; break;
                    }
                }
            }
        }
        if (ok) (*colVerifica)++;
    }
}

int main() {
    int n, m;
    int** matrix = create(&n, &m);
    read(matrix, n, m);
    print(matrix, n, m);

    int k = 5;
    int V_temp[] = {5, 1, 2, 3, 8};
    int* V = malloc(k * sizeof(int));
    for (int i = 0; i < k; i++) {
        V[i] = V_temp[i];
    }

    int colVerifica, rigVerifica;
    verifica_vet_matrice(matrix, n, m, V, k, &colVerifica, &rigVerifica);
}
