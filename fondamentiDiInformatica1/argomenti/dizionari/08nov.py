d = {} # dizionario vuoto, da non confondere con un set che ha un'altra sintassi
d['Luigi'] = 25
d['Mario'] = 22
d['Luigi'] = 26
d['Anna'] = 27
# le chiavi possono essere di tipi primitivi o immutabili, i valori di qualsiasi tipo

d2 = {'a':1, 'b':2, 'c':5}
(type(d2)) # dict

lista = [(1,2), (2,4), (3,6)]
dict(lista) # primo valore tupla: chiave. secondo valore: valore
print('Mario' in d) # cerca la chiave, non la coppia
del d['Mario'] # elimina chiave, di conseguenza la coppia
len(d) # numero chiavi
