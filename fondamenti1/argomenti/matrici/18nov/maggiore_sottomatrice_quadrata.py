def maggiore_sottomatrice_quadrata(matrice):
    ris = []
    nr = len(matrice)
    nc = len(matrice[0])
    dim = nr if nr <= nc else nc
    nc_da_eliminare = nc - dim
    for i in range(dim):
        nuova_riga = []
        for j in range(nc - nc_da_eliminare):
            nuova_riga.append(matrice[i][j + nc_da_eliminare])
        
        ris.append(nuova_riga)
    
    return ris

matrice = [
    [1, 2, 3],
    [4, 5, 6],
    [7, 8, 9],
    [10, 11, 12]
]
for riga in maggiore_sottomatrice_quadrata(matrice):
    print(riga)