import ulm

def cornice(matrice):
    matrice[0] = [1] * len(matrice[0])
    matrice[len(matrice) - 1] = ulm.copia_riga(matrice, 0)

    for riga in matrice:
        riga[0] = 1
        riga[len(matrice[0]) - 1] = 1

    return matrice

ordine = 7
matrice = cornice(ulm.costruisci_matrice_nulla(ordine, ordine))
ulm.stampa_matrice(matrice)