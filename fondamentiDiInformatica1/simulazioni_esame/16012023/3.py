def tasso_guasto(V):
    d_numero_guasti = {}
    d_macchine = {}
    for i in range(len(V)):
        d_macchine = aggiungi_in_dizionario(d_macchine, V[i][1], 1)
        if guasto(V[i]):
            d_numero_guasti = aggiungi_in_dizionario(d_numero_guasti, V[i][1], 1)
    
    d_tasso_guasto = {}
    for tipo in d_macchine:
        if tipo in d_numero_guasti:
            guasti = d_numero_guasti[tipo]
        else:
            guasti = 0
        d_tasso_guasto[tipo] = guasti / d_macchine[tipo]

    return d_tasso_guasto

def guasto(riga):
    return riga[2] == 0 or riga[3] == 0

def aggiungi_in_dizionario(d, chiave, valore):
    if chiave not in d:
        d[chiave] = 0
    d[chiave] += valore
    return d

def elimina_sposta(V, C, citta, tipo):
    tipi = C[citta]
    V = elimina_dalla_matrice(V, tipi)

    for chiave in C:
        for valore in C[chiave]:
            if tipo == valore:
                C[chiave].remove(tipo)
                C[citta].append(tipo)
    print(C)

def elimina_dalla_matrice(V, tipi):
    for tipo in tipi:
        for riga in V:
            if (riga[1] == tipo):
                V.remove(riga)
    return V

def statistiche(V, C):
    d = {}
    for citta in C:
        for riga in V:
            if (riga[1] in C[citta]):
                mattina = riga[2]
                pom = riga[3]
                if citta not in d:
                    d[citta] = [0, 0]
                d[citta][0] += mattina
                d[citta][1] += pom
    return d

V = [
['M1', 'T3', 130, 115],
['M2', 'T2', 0, 125],
['M3', 'T3', 120, 105],
['M4', 'T3', 120, 0],
['M5', 'T1', 110, 100],
['M6', 'T1', 0, 110],
['M7', 'T2', 130, 110],
['M8', 'T3', 120, 100]
]

C = {
    'Napoli' : ['T1', 'T3'],
    'Roma' : ['T2']
}
print(statistiche(V, C))