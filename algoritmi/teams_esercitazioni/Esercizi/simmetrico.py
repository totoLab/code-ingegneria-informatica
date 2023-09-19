from bst import *

"""
Scrivere una funzione "trovaSimmetrico" che riceve in input un albero binario T e un nodo "x",
e restituisce il nodo simmetrico del nodo "x". Il simmetrico di un nodo è definito come il 
nodo che esiste alla posizione simmetrica del nodo x nel sottoalbero opposto alla radice. La funzione
dovrà restituire -1 nel caso in cui il simmetrico di "x" non esista. Si assuma che i contenuti informativi
associati ai nodi di T siano interi positivi.
"""

def trovaSimmetrico(T, x):

    if empty(T):  # un albero vuoto non contiene un simmetrico
        return False
    if value(T) == x:  # il simmetico della radice è la radice stessa
        return x
    # ci muoviamo contemporaneamente su sottoalbero sinistro e destro della radice
    return simmetrico(left(T), right(T), x)


def simmetrico(sinistro, destro, x):
    if empty(sinistro) or empty(destro):
        return -1
    if value(sinistro) == x:
        return value(destro)
    if value(destro) == x:
        return value(sinistro)
    simm = simmetrico(left(sinistro), right(destro), x)
    if simm != -1: return simm
    return simmetrico(right(sinistro), left(destro), x)


if __name__ == '__main__':

    C = [50, 25, 75, 10, 5, 62, 89, 28]

    A = createTree()
    for n in C:
        insert(A, n)

    print(trovaSimmetrico(A, 75))
    print(trovaSimmetrico(A, 62))
    print(trovaSimmetrico(A, 5))