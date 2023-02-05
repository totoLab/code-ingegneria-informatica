import ulm

def somma(matrice):
    nr = len(matrice)
    nc = len(matrice[0])
    s = 0
    for i in range(nr):
        for j in range(nc):
            s += matrice[i][j]
    
    return s

# fare questo ma con una sola iterazione

def somma_matrice_loop_singolo(matrice):
    nr = len(matrice)
    nc = len(matrice[0])
    elementi_totali = nr * nc
    s = 0
    for k in range(elementi_totali):
        riga_corrente = k // nr
        colonna_corrente = k - riga_corrente * nc
        s += matrice[riga_corrente][colonna_corrente]

    return s

matrice = ulm.costruisci_matrice_valori_casuali(5, 5, 99)
ulm.stampa_matrice_incolonnata(matrice, 2)
print("nested loop: ", somma(matrice))
print("single loop: ", somma_matrice_loop_singolo(matrice))