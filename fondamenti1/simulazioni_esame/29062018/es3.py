def aggiungi_in_dizionario(d, elemento, inizializzato, valore):
    if elemento not in d:
        d[elemento] = inizializzato
    d[elemento] += valore

    return d

def massimo_minimo_dizionario(d, massimo_o_minimo):
    massimo = 0
    minimo = float('inf')
    ret = 0
    if massimo_o_minimo:
        for chiave in d:
            if d[chiave] > massimo:
                massimo = d[chiave]
                ret = chiave
    else:
        for chiave in d:
            if d[chiave] < minimo:
                minimo = d[chiave]
                ret = chiave

    return ret

def aula_piu_prenotata(A,P):
    d = {}
    for riga in P:
        chiave = riga[0]
        prenotati = riga[2]
        d = aggiungi_in_dizionario(d, chiave, 0, prenotati)

    return A[massimo_minimo_dizionario(d, True)][0]

def dizionario_settimana(P):
    d_settimana = {}
    
    for prenotazione in P:
        aula = prenotazione[0]
        giorno = prenotazione[1]
        prenotati = prenotazione[2]
        if giorno not in d_settimana:
            d_settimana[giorno] = {}

        d_settimana[giorno] = aggiungi_in_dizionario(d_settimana[giorno], aula, 0, prenotati) 

    return d_settimana

def giorno_massimo_scarto(P):
    d_settimana = dizionario_settimana(P)      

    d_scarti = {}
    for giorno in d_settimana:
        d_giorno = d_settimana[giorno]

        chiave_massima = massimo_minimo_dizionario(d_giorno, True)
        chiave_minima = massimo_minimo_dizionario(d_giorno, False)

        massimo = d_giorno[chiave_massima]
        minimo = d_giorno[chiave_minima]
        scarto = massimo - minimo

        d_scarti = aggiungi_in_dizionario(d_scarti, giorno, 0, scarto)

    return massimo_minimo_dizionario(d_scarti, True)

def verifica(A,P):
    d_settimana = dizionario_settimana(P)
    for giorno in d_settimana:
        d_giorno = d_settimana[giorno]
        if len(d_giorno) == len(A):
            return False

        for aula in d_giorno:
            if d_giorno[aula] > A[aula][1]:
                return False

    return True
    

A = [
    ['32B1', 280],
    ['32B2', 110],
    ['32B3', 110],
    ['P5', 120]
]

P = [
    [0, 1, 90],
    [3, 5, 120],
    [0, 2, 100],
    [2, 2, 90],
    [0, 4, 150],
    [1, 1, 110],
    [2, 1, 30],
    [3, 3, 70],
    [3, 4, 50],
    [0, 2, 120],
    [0, 1, 160]
]
print(aula_piu_prenotata(A, P))
print(giorno_massimo_scarto(P))
print(verifica(A, P))