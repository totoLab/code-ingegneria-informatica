'''
traccia: verifica se esiste almeno un nodo al livello l in T
        e la somma dei valori dei nodi al livello s è maggiore di s 
return boolean
'''
import tree as t

def livelloSomma(T, l, s):
    return livelloSommaR(T, l, s, 0, 0)

def livelloSommaR(T, l, s, distance, somma):
    if l == distance:
        somma += t.value(T)
        if somma > s:
            return True # assumo valori interi positivi
    elif distance < l:
        left = right = False
        if not t.empty(t.right(T)):
            right = livelloSommaR(t.right(T), l, s, distance + 1, somma)
        if not t.empty(t.left(T)):
            left = livelloSommaR(t.left(T), l, s, distance + 1, somma)
        return right or left
    return False

A = t.createTree()
s = [1, 2, 3, 4, 5, 6, 7, 8, 9]
for x in s:
    print(A)
    t.insert(A, x)
    print(A)


print(livelloSomma(A, 2, 4))