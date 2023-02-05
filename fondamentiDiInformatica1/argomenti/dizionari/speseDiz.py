def statistica_spese(spese):
    d = {}
    for riga in spese:
        cliente = riga[0]
        spesa = riga[1]
        if cliente not in d:
            d[cliente] = spesa
        else:
            spesa_finora = d[cliente]
            d[cliente] = spesa_finora + spesa

    return d

def estrai_clienti(spese):
    ret = []
    for riga in spese:
        cliente = riga[0]
        if cliente not in ret:
            ret.append(cliente)

    return ret

def somma_spese(spese, cliente):
    somma = 0
    for riga in spese:
        if riga[0] == cliente:
            somma += riga[1]

    return somma

def stampa_dizionario(d):
    for chiave in d:
        valore = d[chiave]
        print('Chiave', chiave, 'valore', valore)

spese = [
    ['Verdi', 100],
    ['Verdi', 200],
    ['Bianchi', 50],
    ['Neri', 150],
    ['Verdi', 50],
    ['Bianchi', 100]
]
print(somma_spese(spese, 'Verdi'))
stampa_dizionario(d)