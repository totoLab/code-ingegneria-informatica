#include <stdio.h>

#define N 5

int metodoFantasma(int n);

int trovaMax(int vett[], int n) {
    int max = vett[0];
    int i;
    for (i = 1; i < n; i++) {
        if (vett[i] > max) {
            max = vett[i];
        }
    } 
    return max;
}

int main() {
    int x = 0;
    float y = 17.25f;

    printf("x vale %d\ny vale %.2f\n", x, y);
    
    size_t i;
    for (i = 0; i < N; i++) {
        printf("i: %d\n", i);
    }

    int vett[N];
    vett[0] = 1;
    
    printf("vett = [");
    for (i = 1; i < N; i++) {
        vett[i] = vett[i - 1] * 5;
        printf("%d", vett[i]);
        if (i < N - 1) { printf(", "); }
    }
    printf("]\n");

    printf("Inserisci un numero: ");
    scanf("%d", &x);
    printf("Buon anno %d!\n", x);

    int max = trovaMax(vett, N);

    return 0;
}
