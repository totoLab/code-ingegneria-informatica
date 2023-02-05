# 3
# calendario = [
#   ['', pr, pr, '', '', '', pr, '']
#   ['', pr, pr, pr, '', '', pr, '']
#   [pr, '', pr, '', '', pr, pr, '']
#   ['', pr, pr, '', pr, '', pr, '']
# ]

# prenotati = {codice_pr: [cognome, vaccino]}
import ulm
def tutti_uguali(lista):
    if len(lista) == 1:
        return True
    for i in range(len(lista) - 1):
        if lista[i] != lista[i+1]:
            return False

    return True
    
def vaccini_giorno(prenotati, prenotati_g):
    ret = []
    for cf in prenotati_g:
        ret.append(prenotati[cf][1])

    return ret

def prenotati_giorno(calendario, g):
    ret = []
    for i in range(calendario):
        if calendario[i][g] != '':
            ret.append(calendario[i][g])
    
    return ret

def e_omogenea(calendario, prenotati, g):
    prenotati_g = prenotati_giorno(calendario, g)
    vaccini_g = vaccini_giorno(prenotati, prenotati_g)
    return tutti_uguali(vaccini_g)

def giornata_omogenea(calendario, prenotati):
    for g in range(7):
        if e_omogenea(calendario, prenotati, g):
            return g
    
    return -1

def prenotazioni_ordinate(calendario, prenotati):
    for g in range(7):
        if not g_ordinata(calendario, prenotati, g):
            return False
    
    return True

def g_ordinata(calendario, prenotati, g):
    prenotati_g = prenotati_giorno(calendario, g)
    cognomi_g = cognomi(prenotati, prenotati_g)
    return e_ordinata(cognomi_g)

def cognomi(prenotati, prenotati_g):
    ret = []
    for cf in prenotati_g:
        ret.append(prenotati[cf][0])
    
    return ret

def e_ordinata(cognomi_g):
    if len(lista) == 1:
        return True
    for i in range(len(lista) - 1):
        if lista[i] > lista[i + 1]:
            return False

    return True

calendario = [
    ['', 'NRI789', 'CHR123', '', '', '', ''],
    ['LLG234', '', '', 'MNC789', 'VLA987', 'BRN345', ''],
    ['BNC123', 'RSS123', 'PLI123', 'SRR987', '', 'CNT654', ''],
    ['', 'VRD456', 'RSS456', '', '', '', 'RSS765']
]
prenotati = {
    'RSS456': ['Rossi', 'Moderna'],
    'VRD456': ['Verdi', 'Moderna'],
    'NRI789': ['Neri', 'Moderna'],
    'BNC123': ['Bianchi', 'Astrazeneca'],
    'VLA987': ['Viola', 'Astrazeneca'],
    'RSS765': ['Russo', 'J&J'],
    'BRN345': ['Bruno', 'J&J'],
    'CHR123': ['Chiari', 'Pfizer'],
    'CNT654': ['Conte', 'Pfizer'],
    'MNC789': ['Mancini', 'Pfizer'],
    'LLG234': ['Allegri', 'Pfizer'],
    'SRR987': ['Sarri', 'Astrazeneca'],
    'RSS123': ['Rossi', 'Moderna'],
    'PLI123': ['Pioli', 'Moderna']
}
ulm.stampa_matrice_incolonnata(calendario, 6)
print(giornata_omogenea(calendario, prenotati))