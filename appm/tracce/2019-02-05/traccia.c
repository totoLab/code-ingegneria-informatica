#include <stdio.h>
#include <stdlib.h>

int** read(int* n, int* m) {
    printf("Input number of rows: ");
    scanf("%d", n);
    printf("Input number of coloumns: ");
    scanf("%d", m);
    printf("Creating matrix %dx%d\n", *n, *m);
    int** mat;
    mat = malloc(*n * sizeof(int *));
    for (int i = 0; i < *n; i++) {
        mat[i] = malloc(*m * sizeof(int));
        for (int j = 0; j < *m; j++) {
            printf("Input cell (%d,%d): ", i, j);
            scanf("%d", &mat[i][j]);
        }
    }

    return mat;
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

void verifica_vet_matrice(int **M, int n, int m, int* V, int* matVerPos, int* vetVerNeg) {
    int i, j;

    for (i = 0; i < n; i++) {
        int el_neg = 0; 
        for (j = 0; j < m; j++) {
            if (M[i][j] < 0 && M[i][j] > V[j]) {
                el_neg++;
            }
        }
        float result = ((float) el_neg ) / m * 3;
        if (result > 1) (*vetVerNeg)++;
    }
    
    int neg, pos;
    for (j = 0; j < m; j++) {
        neg = 0, pos = 0;
        for (i = 0; i < n; i++) {
            int el = M[i][j];
            if (el > 0 && i % 2 == 0) {
                pos++;
            } else if (el < 0 && i % 2 != 0) {
                neg++;
            }
        }
        if (pos > neg) (*matVerPos)++;
    }
}

int main() {
    int n, m;
    int** matrix = read(&n, &m);

    // Initialize vector V (replace these values with your own)
    int V[] = {2, 4, 6, 8};

    // Declare variables to store results
    int matVerPos = 0, vetVerNeg = 0;

    print(matrix, n, m);
    verifica_vet_matrice(matrix, n, m, V, &matVerPos, &vetVerNeg);
    printf("matVerPos = %d, vetVerNeg = %d\n", matVerPos, vetVerNeg);
}