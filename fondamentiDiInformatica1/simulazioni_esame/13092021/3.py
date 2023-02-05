def aggiungi_dizionario(dizionario, chiave, valore):
    if chiave not in dizionario:
        dizionario[chiave] = 0
    dizionario[chiave] += valore

    return dizionario

def clienti_attivi(R):
    d_clienti = {}
    for prenotazione in R:
        nome = prenotazione[0]
        d_clienti = aggiungi_dizionario(d_clienti, chiave, 1)
        
    highest = 0
    lista_attivi = []
    for cliente in d_clienti:
        if d_clienti[cliente] > highest:
            lista_attivi = []
            lista_attivi.append(cliente)
            highest = d_clienti[cliente]
        elif d_clienti[cliente] == highest:
            lista_attivi.append(cliente)

    return lista_attivi

def scaffale_occupato(L):
    d_scaffali = {}
    for chiave in L:
        scaffale = L[chiave][0]
        libri = L[chiave][1]
        d_scaffali = aggiungi_dizionario(d_scaffali, scaffale, libri)

    highest = 0
    for scaffale in d_scaffali:
        libri = d_scaffali[scaffale]
        if libri > highest:
            highest = libri
            ret = scaffale

    return ret

def rimanenze_libri(R, libro):
    somma = 0
    for prenotazione in R:
        if prenotazione[2] == libro:
            if prenotazione[1] == 'prestito':
                somma -= 1
            elif prenotazione[1] == 'restituzione':
                somma += 1
        
    return somma

            
def gestibili(R, L):
    for libro in L:
        rimanenze = L[libro][1]
        rimanenze += rimanenze_libri(R, libro)
        if rimanenze < 0:
            return False

    return True

def tutti_uguali(lista):
    if len(lista) == 1:
        return True
    for i in range(1, len(lista)):
        if not lista[i] == lista[i-1]:
            return False

    return True

def clienti_veloci(R, L):
    d_operazioni = {}
    d_libri = {}
    for prenotazione in R:
        cliente = prenotazione[0]
        operazione = prenotazione[1]
        libro = prenotazione[2]
        if cliente not in d_operazioni:
            d_operazioni[cliente] = []
        if cliente not in d_libri:
            d_libri[cliente] = []
        d_operazioni[cliente].append(operazione)
        d_libri[cliente].append(L[libro][0])

    veloci = []
    for cliente in d_operazioni:
        lista_operazioni = d_operazioni[cliente]
        lista_libri = d_libri[cliente]
        if tutti_uguali(lista_operazioni) and tutti_uguali(lista_libri):
            veloci.append(cliente)

    return veloci

R = [
    ['Verdi', 'prestito', 'L1'],
    ['Rossi', 'restituzione', 'L2'],
    ['Verdi', 'prestito',  'L3'],
    ['Bianchi', 'prestito', 'L2'],
    ['Rossi', 'prestito',  'L1']
]

L = {
    'L1': ('S1', 2),
    'L2': ('S2', 1),
    'L3': ('S1', 1)
}
print(clienti_veloci(R, L))