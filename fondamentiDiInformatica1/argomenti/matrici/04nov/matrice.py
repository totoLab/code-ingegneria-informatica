# esempio di matrice
# 1 2 3 4
# 5 0 1 4
# 9 8 4 3

def definizione_matrice():
    # una matrice è una lista di liste
    matrice = [
        [1, 2, 3, 4],
        [5, 0, 1, 4],
        [9, 8, 4, 3]
    ]

    # stampa primo elemento matrice
    # print(matrice[0][0])

    # numero righe matrice
    righe = len(matrice)

    # numero colonne matrice
    colonne = len(matrice[0])

    return matrice

def somma_righe_classica(matrix):
    nr = len(matrix)
    nc = len(matrix[0])
    s = 0
    for i in range(nr):
        for j in range(nc):
            s += matrix[i][j]

    return s

def somma_righe_alternativa(matrix):
    s = 0
    for riga in matrix:
        s += sum(riga) 

    return s

def somma_colonne(matrix):
    nr = len(matrix)
    nc = len(matrix[0])
    s = 0
    for j in range(nc):
        for i in range(nr):
           s += matrix[i][j]

    return s

def elimina_riga(matrix, indice_riga):
    del matrice[indice_riga]

def elimina_colonna(matrix, indice_colonna):
    for riga in matrix:
        del riga[indice_colonna]

def costruisci_matrice_identita(ordine):
    matrice = []
    for i in range(ordine):
        riga = [0] * ordine
        matrice.append(riga)

    return matrice

def diagonale_matrice(matrice, diagonale):
    ordine = len(matrice[0]) 
    if diagonale == 0:
        for i in range(ordine):
            matrice[i][i] = 1 # primaria
    elif diagonale == 1:
        for i in range(ordine):
            matrice[i][ordine - i - 1] = 1 # secondaria

    return matrice

def e_identita(matrice):
    nr = len(matrice)
    nc = len(matrice[0])

    if nr != nc:
        return False

    for i in range(nr):
        for j in range(nc):
            if i == j and matrice[i][j] != 1 or i != j and matrice[i][j] != 0:
                return False
    
    return True
    

#matrice = definizione_matrice()
#print(somma_righe_classica(matrice))
#print(somma_righe_alternativa(matrice))
#print(somma_colonne(matrice))
#
#elimina_riga(matrice, 0)
#elimina_colonna(matrice, 0)
#
#identita = costruisci_matrice_identita(10)
#
#matrixes = [matrice, identita]
#for i in range(len(matrixes)):
#    for riga in matrixes[i]:
#        print((matrixes[i], e_identita(matrixes[i])))

# da fare - cornice di matrice quadrata con diagonali opposte
