t = (1, 2, 3) # uguale ma scoraggiato t = 1, 2, 3
type(t) #oggetto tupla

# non mutabile, eterogenea

q = (10, ) # tupla con un solo elemento, o "singleton"

# tuple unpacking
a, b, c = t

# modifica elemento mutabile in tupla (immutabile)
t = (1, 2, [3, 4, 5])
t[2][1] = 100

# modifica tupla in lista
lista = [1, 2, (3, 4)]
lista[2][0] = 20 # errore, immutabile
lista[2] = (20, 4) # qui modifichi l'elemento della lista (mutabile)