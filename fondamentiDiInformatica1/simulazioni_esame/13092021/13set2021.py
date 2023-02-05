# prestito e restituzione libri biblioteca durante UNA giornata lavorativa
# prenotazioni da evadere:
# nome, operazione da svolgere ('presito', 'restituzione'), codice_libro

# libri = {codice_libro: (codice_scaffale, copie)}


def clienti_attivi(matrice_prenotazioni):
    clienti = {}
    for prenotazione in matrice_prenotazioni:
        cognome = prenotazione[0]
        if cognome in clienti:
            clienti[cognome] += 1
        else:
            clienti[cognome] = 1

    max = 0
    lista_attivi = []
    for cliente in clienti:
        numero_operazioni = clienti[cliente]
        if numero_operazioni > max:
            max = numero_operazioni
            lista_attivi = [cliente]
        elif numero_operazioni == max:
            lista_attivi.append(cliente)

    return lista_attivi

def scaffale_occupato(libri):
    scaffali = {}
    for libro in libri:
        scaffale = libri[libro][0]
        numero_libri = libri[libro][1]
        if scaffale in scaffali:
            scaffali[scaffale] += numero_libri
        else:
            scaffali[scaffale] = numero_libri

    max = 0
    for scaffale in scaffali:
        numero_libri = scaffali[scaffale]
        if numero_libri >= max:
            max = numero_libri
            scaffale_pieno = scaffale

    return scaffale_pieno

def gestibili(prenotazioni, libri):
    somma_libri = {}
    for libro in libri:
        copie = libri[libro][1]
        if libro in somma_libri:
            somma_libri[libro] += copie
        else:
            somma_libri[libro] = copie

    for cliente in prenotazioni:
        if cliente[1] == 'restituzione':
            somma_libri[cliente[2]] += 1
        else:
            somma_libri[cliente[2]] -= 1

    for libro in somma_libri:
        if somma_libri[libro] < 0:
            return False

    return True
    # libro: copie + restituzioni - prestito
    # if differenza[libro] >= 0: return True

def clienti_veloci(prenotazioni, libri):
    pass

L = {
    'L1': ('S1', 2),
    'L2': ('S2', 1),
    'L3': ('S1', 2)
}

R = [
    ['Verdi', 'prestito', 'L1'],
    ['Rossi', 'restituzione', 'L2'],
    ['Verdi', 'prestito', 'L3'],
    ['Bianchi', 'prestito', 'L2'],
    ['Rossi', 'prestito', 'L1'],
]

print(clienti_veloci(R, L))