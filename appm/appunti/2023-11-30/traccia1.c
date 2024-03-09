#include <stdio.h>
#include <stdlib.h>

// traccia: implementa i seguenti metodi
void erase(int **mat, int n, int m); // ogni elemento a 0
int ** create(int n, int m);         // malloc
void printMat(int **mat, int n, int m); // pretty print
void read(int **mat, int n, int m);  // from keyb

// esercizio vero:
void media_tutti_positivi(int **matr, int n, int m, float *media, int *tutti_positivi);

int main() {

    int n, m, i, j;
    int **mat;

    printf("Inserisci numero righe: ");
    scanf("%d", &n);
    printf("\n");
    
    printf("Inserisci numero colonne: ");
    scanf("%d", &m);
    printf("\n");
    
    mat = create(n, m);
    erase(mat, n, m);
    printMat(mat, n, m);
    read(mat, n, m);
    printMat(mat, n, m);

    int tutti_positivi; 
    float media;
    media_tutti_positivi(mat, n, m, &media, &tutti_positivi);
    if (tutti_positivi) {
        printf("Tutti positivi, media -> %0.2f\n", media);
    } else {
        printf("Non tutti positivi\n");
    }
    return 0;
}

void erase(int **mat, int n, int m) {
    int i, j;
    for (i = 0; i < n; i++) {
        for (i = 0; i < n; i++) {
            mat[i][j] = 0;
        }
    }
} 

int ** create(int n, int m) {
    int **matr;
    matr = (int **) malloc(n * sizeof(int *));
    
    int i;
    for (i = 0; i < n; i++) {
        matr[i] = (int *) malloc(m * sizeof(int));
    }
    return matr;
}   

void printMat(int **mat, int n, int m) {
    printf("[\n");
    for (int i = 0; i < n; i++) {
        printf("  [");
        for (int j = 0; j < m; j++) {
            printf("%d", mat[i][j]);
            if ((1 + j) % m != 0) printf(", ");
        }
        printf("],\n");
    }
    printf("]\n");
}

void read(int **mat, int n, int m) {
    int i, j;
    for (i = 0; i < n; i++) {
        for (j = 0; j < m; j++) {
            printf("Inserisci l'elemento in posizione (%d,%d) ", i, j);
            scanf("%d", &mat[i][j] );
        }
    }
} 

void media_tutti_positivi(int **matr, int n, int m, float *media, int *tutti_positivi) {
    int somma;
    *tutti_positivi = 1;
    int i, j;
    for (i = 0; i < n; i++) {
        for (j = 0; j < m; j++) {
            somma += matr[i][j];
            if (matr[i][j] < 0) {
                *tutti_positivi = 0;
            }
        }
    }    
    *media = (float) somma / (n * m);
}
