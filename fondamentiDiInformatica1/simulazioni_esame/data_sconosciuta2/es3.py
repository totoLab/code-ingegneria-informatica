def potenziale_infetto(posti, i, j , dipendenti, k):
    for r in range(i - k, i + k + 1):
        for c in range(j - k, j + k + 1):
            if 0 <= r < len(posti) and 0 <= c < len(posti[0]) and e_positivo(dipendenti, posti[r][c]):
                return True
    
    return False

def e_positivo(dipendenti, matricola):
    return dipendenti[matricola][3]

def infetti_e_positivi(posti, dipendenti, k):
    ret = []
    for i in range(len(posti)):
        for j in range(len(posti[0])):
                if potenziale_infetto(posti, i, j, dipendenti, k):
                    ret.append(posti[i][j])
    
    return ret

def dipendenti_potenziali_infetti(posti, dipendenti, k):
    ret = []
    for matricola in infetti_e_positivi(posti, dipendenti, k):
        if not e_positivo(dipendenti, matricola):
            ret.append(matricola)

    return ret

def infetti_citta(posti, dipendenti, k):
    d = {}
    for matricola in infetti_e_positivi(posti, dipendenti, k):
        citta = dipendenti[matricola][2]
        if citta in d:
            d[citta] += 1
        else:
            d[citta] = 1

    return d

## contagiosità critica

posti = [
    ['A', 'B', 'C'],
    ['D', 'E', 'F'],
    ['G', 'H', 'I'],
    ['L', 'M', 'N']
]

dipendenti = {
    'A': ['marco', 'rossi', 'rende', True],
    'B': ['carlo', 'verdi', 'rende', False],
    'C': ['gino', 'neri', 'cosenza', False],
    'D': ['franco', 'bianchi', 'cosenza', True],
    'E': ['diego', 'viola', 'rossano', False],
    'F': ['nino', 'russo', 'rossano', False],
    'G': ['peppe', 'bruno', 'reggio', False],
    'H': ['mario', 'chiari', 'reggio', False],
    'I': ['ciro', 'ferrara', 'napoli', False],
    'L': ['rino', 'sacchi', 'napoli', False],
    'M': ['ugo', 'fantozzi', 'roma', False],
    'N': ['andrea', 'piaggio', 'roma', True]
}
k = 1

print(infetti_citta(posti, dipendenti, k))