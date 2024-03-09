def distributori_simili(V, cod):
    tipo_verifica = V[0][1]
    incasso_verifica = V[0][2]
    for i in range(len(V)):
        riga = V[i]

        codice = riga[0]
        tipo = riga[1]
        incasso = riga[2]

        if codice == cod:
            tipo_verifica = tipo
            incasso_verifica = incasso

    ret = []
    for i in range(len(V)):
        riga = V[i]

        codice = riga[0]
        tipo = riga[1]
        incasso = riga[2]

        if codice != cod and tipo == tipo_verifica and incasso == incasso_verifica:
            ret.append(codice)
    
    return ret

def tipo_schedato(d, key):
    for i in range(len(d)):
        chiave = d[i][0]
        if chiave == key:
            return i
    return -1

def affidabilita_tipi(V):
    dati = []
    for i in range(len(V)):
        riga = V[i]

        tipo = riga[1]
        incasso_mat, incasso_pom = riga[2], riga[3]

        indice = tipo_schedato(dati, tipo)
        if indice == -1:
            dati.append((tipo, [0, 0]))
            indice = len(dati) - 1

        distributore = dati[indice]

        distributore[1][0] += 1 # numero distributori
        if not(incasso_mat == 0 or incasso_pom == 0):
            distributore[1][1] += 1 # numero non guasti

    ret = []
    for i in range(len(dati)):
        entry = dati[i]
        numero, guasti = entry[1]
        ret.append((entry[0], guasti/numero))

    return ret

def trova_citta(C, tipo):
    for i in range(len(C)):
        citta = C[i][0]
        lista = C[i][1]
        if tipo in lista:
            return citta
    return None

def statistiche(V, C):
    dati = []
    for i in range(len(V)):
        riga = V[i]

        tipo = riga[1]
        citta = trova_citta(C, tipo) # assumo che il tipo sia sempre in qualche lista di C
        incasso_mat, incasso_pom = riga[2], riga[3]

        indice = tipo_schedato(dati, citta)
        if indice == -1:
            dati.append((citta, [0, 0]))
            indice = len(dati) - 1

        lista_incassi = dati[indice][1]
        lista_incassi[0] += incasso_mat
        lista_incassi[1] += incasso_pom
    return dati

'''
def elimina_sposta(V, C, citta, tipo):
    lista_eliminare = []
    for i in range(len(C)):
        riga = V[i]

        if riga[0] == citta:
            lista_eliminare = riga[1]
    
    new_V = []
    for i in range(len(V)):
        riga = V[i]

        if riga[1] not in lista_eliminare:
            new_V.append(riga)
    V = new_V

    lista_spostare = []
    indice = 0
    for i in range(len(C)):
        riga = V[i]

        if riga[0] == citta:
            lista_spostare = riga[1]
            indice = i

    C.remove(i)
    for for i in range(len(C)):
        riga = V[i]
'''

data = [
    ["D1", "T3", 130.0, 115.0],
    ["D2", "T2", 0.0, 125.0],
    ["D3", "T3", 120.0, 105.0],
    ["D4", "T3", 120.0, 0.0],
    ["D5", "T1", 110.0, 100.0],
    ["D6", "T1", 0.0, 110.0],
    ["D7", "T2", 130.0, 110.0],
    ["D8", "T3", 120.0, 100.0]
]
C=[
    ('Rende',   ['T1', 'T3']),
    ('Cosenza', ['T2']      )
]

#print(distributori_simili(data, "D4"))
print(affidabilita_tipi(data))
#print(statistiche(data, C))
