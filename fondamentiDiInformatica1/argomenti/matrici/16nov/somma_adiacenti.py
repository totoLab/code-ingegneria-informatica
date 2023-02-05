import ulm
def somma_adiacenti(matrice, i, j):
    s = 0
    nr = len(matrice)
    nc = len(matrice[0])
    # upper row
    if i != 0:
        s += matrice[i - 1][j]
        if j != 0:
            s += matrice[i - 1][j - 1]
        if j != nc - 1:
            s += matrice[i - 1][j + 1]
    # middle row
    if j != 0:
        s += matrice[i][j - 1]
    if j != nc - 1:
        s += matrice[i][j + 1]
    # bottom row
    if i != nr - 1:
        s += matrice[i + 1][j]            
        if j != 0:
            s += matrice[i + 1][j - 1]
        if j != nc - 1:
            s += matrice[i + 1][j + 1]

    return s

def matrice_somme_adiacenti(matrice):
    nr = len(matrice)
    nc = len(matrice[0])
    ret = ulm.costruisci_matrice_nulla(nr, nc)
    for i in range(nr):
        for j in range(nc):
            ret[i][j] = somma_adiacenti(matrice, i, j)

    return ret

def main():
    incolonnamento = 3
    matrice = ulm.costruisci_matrice(8, 10, 1)
    ulm.stampa_matrice_incolonnata(matrice, incolonnamento)
    print()
    A = matrice_somme_adiacenti(matrice)
    ulm.stampa_matrice_incolonnata(A, incolonnamento)

main()