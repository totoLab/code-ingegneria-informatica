def coordinate_positivi(posti, dipendenti):
    positivi = []
    for i in range(len(posti)):
        for j in range(len(posti[0])):
            dipendente = posti[i][j]
            if dipendenti[dipendente][3]:
                positivi.append((i,j))

    return positivi

def potenziale_infetto(posti, i, j, dipendenti, k):
    for positivo in coordinate_positivi(posti, dipendenti):
        riga_positivo = positivo[0]
        colonna_positivo = positivo[1]
        if abs(riga_positivo - i) <= k:
            if abs(colonna_positivo - j) <=  k:
                return True
    return False

def tutti_potenziali_infetti(posti, dipendenti, k):
    potenziali_infetti = []
    for i in range(len(posti)):
        for j in range(len(posti[0])):
            if potenziale_infetto(posti, i, j, dipendenti, k):
                potenziali_infetti.append(posti[i][j])

    return potenziali_infetti

def dipendenti_potenziali_infetti(posti,dipendenti,k):
    positivi = coordinate_positivi(posti, dipendenti)
    potenziali_infetti = tutti_potenziali_infetti(posti, dipendenti, k)
    for dipendente in potenziali_infetti:
        if dipendente in positivi:
            potenziali_infetti.pop(dipendente)

    return potenziali_infetti

def infetti_citta(posti,dipendenti,k):
    dipendenti_citta = {}
    infetti = tutti_potenziali_infetti(posti, dipendenti, k)
    for infetto in infetti:
        citta = dipendenti[infetto][2]
        if citta not in dipendenti_citta:
            dipendenti_citta[citta] = 0
        dipendenti_citta[citta] += 1

    return dipendenti_citta

def contagiosita_critica(posti, dipendenti):
    max_righe_colonne = max(len(posti), len(posti[0]))
    for k in range(max_righe_colonne, 0, -1):
        d_infetti_citta = infetti_citta(posti, dipendenti, k)
        for chiave in d_infetti_citta:
            if d_infetti_citta[chiave] == 0:
                return k + 1
    return k

posti = [
    ['A','B','C'], 
    ['D','E','F'], 
    ['G','H','I'], 
    ['L','M','N']
] 
 
dipendenti = {
    'A':['marco','rossi','rende',True],
    'B':['carlo','verdi','rende',False],
    'C':['gino','neri','cosenza',False],
    'D':['franco','bianchi','cosenza',True],
    'E':['diego','viola','rossano',False],
    'F':['nino','russo','rossano',False],
    'G':['peppe','bruno','reggio',False],
    'H':['mario','chiari','reggio',False],
    'I':['ciro','ferrara','napoli',False],
    'L':['rino','sacchi','napoli',False],
    'M':['ugo','fantozzi','roma',False],
    'N':['andrea','piaggio','roma',True]
}

print(contagiosita_critica(posti, dipendenti))