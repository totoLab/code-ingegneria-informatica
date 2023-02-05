def meno_movimenti(M,C):
    citta = {}
    for movimento in M:
        movimento = movimento[0]
        if movimento not in citta:
            citta[movimento] = 0
        citta[movimento] += 1

    minimo = len(M) + 1
    for chiave in citta:
        if citta[chiave] < minimo:
            ret = C[chiave][0]
            minimo = citta[chiave]

    return ret

def estrai_saldi(M,C):
    saldi_cc = {}
    for movimento in M:
        if movimento[0] not in saldi_cc:
            saldi_cc[movimento[0]] = 0
        saldi_cc[movimento[0]] += movimento[2]

    return saldi_cc

def filtra_clienti(saldi_cc, C):
    clienti_filtrati = []
    for cc in saldi_cc:
        if saldi_cc[cc] > 0:
            clienti_filtrati.append(C[cc][1])

    return clienti_filtrati

def conta_cc_positivi(clienti):
    conteggio = {}
    for nome in clienti:
        if nome not in conteggio:
            conteggio[nome] = 0
        conteggio[nome] += 1

    clienti_target = []
    for chiave in conteggio:
        if conteggio[chiave] >= 2:
            clienti_target.append(nome)

    return clienti_target

def clienti_target(M,C):
    saldi_cc = estrai_saldi(M,C)
    clienti_filtrati = filtra_clienti(saldi_cc, C)
    due_o_piu_positivi = conta_cc_positivi(clienti_filtrati)
    return due_o_piu_positivi
        
M = [
    [0,1,100.0],
    [3,1,100.0],
    [2,2,200.0],
    [0,2,-300.0],
    [2,3,-400.0],
    [0,4,100.0],
    [0,5,200.0],
    [1,9,-100.0],
    [2,9,400.0]
]

C = {
    0 : ['Rende','Rossi'],
    1 : ['Cosenza','Verdi'],
    2 : ['Crotone','Rossi'],
    3 : ['Rossano','Verdi'],
}

print(clienti_target(M,C))











