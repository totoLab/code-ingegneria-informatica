def aggiungi_in_dizionario(dizionario, chiave, valore):
    if chiave not in dizionario:
        dizionario[chiave] = 0
    dizionario[chiave] += valore
    return dizionario

def converti_dizionario_in_matrice(dizionario):
    M = []
    for chiave in dizionario:
        valore = dizionario[chiave]
        M.append([chiave, valore])
    return M

def spese_per_tipo(S):
    d = {}
    for riga in S:
        tipo = riga[1]
        ammontare = riga[2]
        d = aggiungi_in_dizionario(d, tipo, ammontare)

    return converti_dizionario_in_matrice(d)

S = [
    [0, "Personale", 120],
    [3, "Missioni", 20],
    [0, "Attrezzature", 10],
    [2, "Consulenze", 30],
    [0, "Missioni", 20],
    [1, "Personale", 40],
    [2, "Personale", 50],
    [3, "Attrezzature", 50],
    [3, "Personale", 250],
    [0, "Consulenze", 10],
    [0, "Missioni", 10],
]

### extra
def printMatrix(M):
    for riga in M:
        print(riga)

printMatrix(spese_per_tipo(S))