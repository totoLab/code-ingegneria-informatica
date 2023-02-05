def istogramma(voti):
    d = {}
    voti_visti = []
    for voto in voti:
        if voto not in voti_visti:
            d[voto] = voto.count(voto)
    
    return voto

def stampa_dizionario(d):
    for chiave in d:
        valore = d[chiave]
        print('Chiave', chiave, 'valore', valore)

voti = [25,26,27,27,28,25,29,30,27,26]
d = istogramma(voti)
stampa_dizionario(d)