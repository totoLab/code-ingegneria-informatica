# 3.1
def filiali_ritardatarie(M):
    filiali_ritardo = genera_filiali_ritardo(M)
    
    massimo = -1
    lista_ritardatarie = []
    for filiale in filiali_ritardo:
        ritardo = filiali_ritardo[filiale]
        if ritardo > massimo:
            massimo = ritardo
            lista_ritardatarie = [filiale]
        elif ritardo == massimo:
            lista_ritardatarie.append(filiale)
    
    return lista_ritardatarie

def calcola_ritardo(M, ordine):
    return M[ordine][1] - M[ordine][3]    

def genera_filiali_ritardo(M):
    filiali_ritardo = {}
    for ordine in range(len(M)):
        filiale = M[ordine][0]
        if filiale not in filiali_ritardo:
            filiali_ritardo[filiale] = 0
        filiali_ritardo[filiale] += calcola_ritardo(M, ordine)

    return filiali_ritardo

def genera_filiali_prezzo(M):
    filiali_prezzo = {}
    for ordine in range(len(M)):
        filiale = M[ordine][0]
        prezzo = M[ordine][2]
        if filiale not in filiali_prezzo:
            filiali_prezzo[filiale] = 0
        filiali_prezzo[filiale] += prezzo

    return filiali_prezzo

# 3.2
def statistiche_citta(M, D):
    filiali_prezzo = genera_filiali_prezzo(M)

    citta_prezzo = {}
    for filiale in filiali_prezzo:
        citta = D[filiale]
        prezzo = filiali_prezzo[filiale]
        if citta not in citta_prezzo:
            citta_prezzo[citta] = 0
        citta_prezzo[citta] += prezzo
    
    return citta_prezzo

# 3.3
def filiale_migliore(M, f):
    filiali_ritardo = genera_filiali_ritardo(M)
    filiali_prezzo = genera_filiali_prezzo(M)

    for filiale in filiali_ritardo:
        if filiali_ritardo[filiale] < filiali_ritardo[f] and filiali_prezzo[filiale] > filiali_prezzo[f]:
            return filiale
    
    return ""

# 3.4
def citta_limitate(M, D, d, k):
    citta_consegne = {}
    for riga in M:
        data = riga[1]
        if data <= d:
            filiale = riga[0]
            citta = D[filiale]
            if citta not in citta_consegne:
                citta_consegne[citta] = 0
            citta_consegne[citta] += 1

    lista_citta_limitate = []
    for citta in citta_consegne:
        numero_consegne = citta_consegne[citta]
        if numero_consegne < k:
            lista_citta_limitate.append(citta)

    return lista_citta_limitate


M = [
    ["Filiale A", 10, 5, 8],
    ["Filiale B", 12, 10, 12],
    ["Filiale C", 6, 5, 4],
    ["Filiale D", 4, 5, 4],
    ["Filiale E", 8, 10, 7],
    ["Filiale F", 6, 15, 5],
    ["Filiale A", 10, 10, 9],
    ["Filiale B", 11, 5, 11],
    ["Filiale C", 6, 5, 5],
    ["Filiale D", 11, 10, 10],
]

D = {
    "Filiale A": "Cosenza",
    "Filiale B": "Rende",
    "Filiale C": "Reggio Calabria",
    "Filiale D": "Rende",
    "Filiale E": "Catanzaro",
    "Filiale F": "Catanzaro"
}

f = "Filiale E"
d = 10
k = 2
print(filiali_ritardatarie(M))
print(statistiche_citta(M, D))
print(filiale_migliore(M, f))
print(citta_limitate(M, D, d, k))