import ulm

matrice = ulm.costruisci_matrice_identita(10)
doppia_diagonale = ulm.diagonale_matrice(ulm.diagonale_matrice(matrice, 0), 1)

ordine = len(matrice[0])

matrice[0] = [1] * len(matrice[0])
matrice[ordine - 1] = [1] * ordine

for riga in matrice:
    riga[0] = 1
    riga[ordine - 1] = 1

for riga in doppia_diagonale:
    print(riga)