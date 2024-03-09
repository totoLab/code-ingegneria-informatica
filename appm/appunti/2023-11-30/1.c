#include <stdio.h>
#include <stdlib.h>

void pointers_intro(); 
void scambia();
void scambia_valori(int x, int y);
void scambia_reference(int* x, int* y);
void scambia_puntatori(int** x, int** y);
int dynamic_vector();
int matrix();
void enums();

int main() {
    //pointers_intro();
    //scambia();
    //dynamic_vector();
    matrix();
}

void pointers_intro() {
    int* puntx;
    // puntx = 100; // assegnamento diretto dell'indirizzo (sconsigliato)
    int x = 10;
    puntx = &x;
    printf("x = %d\n", x);

    int y = *puntx; // copia valore del puntatore
    printf("y = %d\n", y);

    int* punty = puntx; // copia puntatore
    printf("x*, y* = %p, %p\n", puntx, punty);

    // y = y + 1 equivale a
    punty = &y;
    *punty = *punty + 1;
    printf("y = %d, y* = %p\n", y, punty);
    
    int* puntz = (int*) 1580000;
    printf("%p", puntz);
}

void scambia() {
    int x = 10; int y = 5;
    printf("x = %d, y = %d\n", x, y);
    int* px = &x; int* py = &y;
    // scambia_valori(x, y);
    // scambia_reference(&x, &y);
    // scambia_puntatori(&px, &py);
    printf("x = %d, y = %d\n", x, y);
}

void scambia_valori(int x, int y) {
    int temp = x;
    x = y;
    y = temp;
}

void scambia_reference(int* px, int* py) {
    int temp = *px;
    *px = *py;
    *py = temp;
}

void scambia_puntatori(int** px, int** py) {
    int* temp = *px;
    *px = *py;
    *py = temp;
}

int static_vector() {
    int vett[5] = {0};
    // vett == &vett[0] per definizione

    // equivalenti
    *vett = 18;
    vett[0] = 18;

    *(vett + 2) = 54; // 2 indirizzi successivi all'inizio del vettore
}

int dynamic_vector() {
    // dichiarazione
    int n;
    double *vett;

    printf("Inserisci la dimensione di vett> ");
    scanf("%d", &n);

    // allocazione con malloc() che restuisce un certo numero di byte, di cui va fatto il casting per definire il tipo
    vett = (double *) malloc(n * sizeof(double));

    for (int i = 0; i < n; i++) {
        printf("Inserisci elemento %d del vettore:", i );
        scanf("%lf", &vett[i]); // &vett + i
    }
    
    printf("vett = [");
    for (int i = 0; i < n; i++) {
        printf("%lf, ", vett[i]);
    }
    printf("]");

}

int matrix() {
    // static
    int matrix[3][4];

    // dynamic: due implementazioni
    // 1. vettore con indicizzazione mat[i][j] = vett[i * m + j] NO.
    // 2. vettore di righe <-> vettore di puntatori ad altri vettori
    int n = 3, m = 4;
    int **mat;

    mat = (int **) malloc(n * sizeof(int*));
    for (int i = 0; i < n; i++) {
        mat[i] = (int *) malloc(m * sizeof(int));
        for (int j = 0; j < m; j++) {
            mat[i][j] = 7;
        }
    }

    printf("mat = [\n");
    for (int i = 0; i < n; i++) {
        printf("  [");
        for (int j = 0; j < m; j++) {
            printf("%d", mat[i][j]);
            if ((1 + j) % m != 0) printf(", ");
        }
        printf("],\n");
    }
    printf("]\n");

    // dopo l'utilizzo di uno spazio di memoria va liberato lo spazio allocato
    for (int i = 0; i < n; i++) {
        free(mat[i]);
    }
    free(mat);
}


typedef enum {
    quadri, picche, cuori, fiori
} Seme;

typedef enum {
    false, true
} Bool;

void enums() {
    Seme seme;
    seme = quadri;

    Bool trovato;
    trovato = false;
    if (trovato) { // funziona solo perché per C: != 0 -> true
        // ...
    }
}