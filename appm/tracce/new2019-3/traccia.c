#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <float.h>

float** create(int n, int m) {
    float** matrix = malloc(n * sizeof(float*));
    for (int i = 0; i < m; i++) {
        matrix[i] = malloc(m * sizeof(float));
    }
    return matrix;
}

void read(float** matrix, int n, int m) {
    int i, j;
    for (i = 0; i < n; i++) {
        for (j = 0; j < m; j++) {
            printf("Input cell (%d, %d): ", i, j);
            scanf("%f", &matrix[i][j]);
        }
    }
}

void print(float** matrix, int n, int m) {
    int i, j;
    for (i = 0; i < n; i++) {
        printf("|");
        for (j = 0; j < m; j++) {
            if (j < m - 1) printf("%3f, ", matrix[i][j]);
            else printf("%3f|\n", matrix[i][j]);
        }
    }
}

void verifica_vet_matrice(
    float** matrix, int n, int m,
    float* vet, int* vetMinimo, int* vetSbagliato) {

    int i, j, k;
    for (i = 0; i < n; i++) {
        int esistenza_1 = 1, esistenza_2 = 1, posizione_ok = 1;

        for (j = 0; j < m; j++) {
            float el_m = matrix[i][j];
            for (k = 0; k < m; k++) {
                float el_v = vet[k];
                if (el_m == el_v) {
                    esistenza_1 = 0;
                    if (j != k) posizione_ok = 0;
                }
            }
        }
            
        for (k = 0; k < m; k++) {
            float el_v = vet[k];
            for (j = 0; j < m; j++) {
                float el_m = matrix[i][j];
                if (el_m == el_v) esistenza_2 = 0;
            }
        }

        if (esistenza_1 && esistenza_2 && posizione_ok) (*vetSbagliato)++;
    }

    float min = FLT_MAX;
    for (i = 0; i < n; i++) {
        for (j = 0; j < m; j++) {
            float el = matrix[i][j];
            if (el < min) min = el; 
        }
    }

    for (j = 0; j < m; j++) {
        int ok = 0;
        for (i = 0; i < n; i++) {
            float el = matrix[i][j];
            if ((int)el % (int)min != 0) ok = 1; 
        }
        if (ok) (*vetMinimo)++;
    }
}

int main() {
    int n = 2, m = 2;
    float** matrix = create(n, m);
    read(matrix, n, m);
    print(matrix, n, m);

    float* vet = malloc(m * sizeof(float)); 
    float vet_temp[] = {4, 2};
    for (int i = 0; i < m; i++) {
        vet[i] = vet_temp[i];
    }

    int vetMinimo = 0, vetSbagliato = 0;
    verifica_vet_matrice(matrix, n, m, vet, &vetMinimo, &vetSbagliato);
    printf("vetMinimo = %d, vetSbagliato = %d", vetMinimo, vetSbagliato);
}