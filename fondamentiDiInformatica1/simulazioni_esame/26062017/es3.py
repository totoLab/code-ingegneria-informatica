# funzioni di costruzione
def aggiungi_al_dizionario(d, chiave, inizializzato, valore):
    if chiave not in d:
        d[chiave] = inizializzato
    d[chiave] += valore

    return d

def massimo_in_dizionario(d):
    massimo = 0
    ret = 0
    for chiave in d:
        if d[chiave] > massimo:
            massimo = d[chiave]
            ret = chiave

    return ret

def somma_riga(matrice, riga):
    return sum(matrice[riga])

def somma_colonna(matrice, colonna):
    somma = 0
    for riga in matrice:
        somma += riga[colonna]

    return somma

# 3.1
def vittorie(T):
    set_coppie = set() # using set instead of list because of duplicates
    for i in range(len(T)):
        for j in range(len(T[0])):
            if i != j:
                if T[i][j] > T[j][i]:
                    set_coppie.add((i, j))
                elif T[j][i] > T[i][j]:
                    set_coppie.add((j, i))

    return list(set_coppie) # conversion

# 3.2
def migliore(T):
    lista_vincitori = vittorie(T)
    d = {}
    for elemento in lista_vincitori:
        elemento = elemento[0]
        d = aggiungi_al_dizionario(d, elemento, 0, 1)
    
    return massimo_in_dizionario(d)

# 3.3
def massimo_scarto(T):
    d_scarti = {}
    for i in range(len(T)):
        scarto = somma_riga(T, i) - somma_colonna(T, i)
        d_scarti = aggiungi_al_dizionario(d_scarti, i, 0, scarto)

    return massimo_in_dizionario(d_scarti)

T = [
    [0,8,2,5,1], 
    [3,0,2,2,5], 
    [6,7,0,4,7], 
    [3,3,5,0,3], 
    [7,8,8,8,0]
]

print(vittorie(T))
print(migliore(T))
print(massimo_scarto(T))