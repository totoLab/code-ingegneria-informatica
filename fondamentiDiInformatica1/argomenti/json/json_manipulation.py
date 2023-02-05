# creazione oggetto
dati = {
    'nome': 'Mario',
    'cognome': 'Rossi',
    'hobby': ['corsa', 'divano', 'mangiare'],
    'età': 35
}

# de/serializzazione da variabile
stringa = json.dumps()

dati = json.loads(stringa)

# de/serializzazione su disco
f = open('file-dati.txt', w)
json.dump(f)
f.close()

f = open('file-dati.txt', r)
dati = json.load(f)
f.close()


#