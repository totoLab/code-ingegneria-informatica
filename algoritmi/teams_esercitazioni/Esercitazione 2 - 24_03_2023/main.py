from bst import *


"""
Esercizio 1.
Scrivere due funzioni, sommaNodi e sommaFoglie, che ricevono in ingresso un albero A.
La prima restituisce la somma di tutti i nodi dell'albero, la seconda restituisce la somma 
dei soli nodi fogli
"""

def somma(A):
    """
    somma le informazioni associate ai nodi dell'albero A
    :param A: Albero
    :return: int
    """
    if empty(A):
        return 0
    return value(A) + somma(left(A)) + somma(right(A))


def isLeaf(A):
    return empty(left(A)) and empty(right(A))


def sommaFoglie(A):
    """
    Calcola la somma dei nodi foglia presenti in A
    :param A: Albero
    :return: int
    """
    if empty(A):
        return 0
    if isLeaf(A):
        return value(A)
    return sommaFoglie(left(A)) + sommaFoglie(right(A))

"""
Esercizio 2.

Dati due alberi binari di ricerca A e B tali che le chiavi in A sono tutte minori delle chiavi in B, scrivere
una funzione che restituisce un albero di ricerca contenente tutte le chiavi di A e B.
"""

def findMaxNode(A):

    if empty(right(A)):
        return A
    return findMaxNode(right(A))


def concatenazione(A, B):
    """
    Concatena due alberi A e B, tali che tutte le chiavi presenti in A sono minori delle chiavi di B.
    :param A: ABR
    :param B: ABR
    """

    C = findMaxNode(A)

    setRight(C, B)


"""
Esercizio 3

Si scriva una funzione isABR che, dato un albero A in ingresso,
verifichi se A è un albero di ricerca (ovvero soddisfi le proprietà di ricerca). 
In particolare, la funzione deve restituire True se A è un ABR, False altrimenti.

"""

def verificaABR(A):
    """

    :param A: ABR
    :return: True se A è un ABR, ovvero rispetta le proprietà di ricerca.
    Per ogni nodo (v) le chiavi del sottoalbero sinistro sono
    non maggiori di v, e quelle del sottoalbero destro non non minori di v. Il metodo restituisce
    False altrimenti.
    """
    return verifica2(A, float("-inf"), float("+inf"))


def verifica2(A, min, max):
    if empty(A):
        return True
    if min <= value(A) <= max:
        return verifica2(left(A), min, value(A)) and verifica2(right(A), value(A),max)
    return False


"""
Esercizio 4.

Si scriva una funzione larghezza(A) che dato un albero A in ingresso, restituisca un valore intero indicante la larghezza di A. 

La larghezza di un albero è il numero massimo di nodi che stanno al medesimo livello.
"""

def larghezza2(A, L, livello=0):
    if not empty(A):
        L[livello] += 1
        larghezza2(left(A), L, livello+1)
        larghezza2(right(A), L, livello+1)

def larghezza(A):
    """
    Calcola la larghezza di un albero A
    :return: int
    """
    L = [0] * depth(A)
    larghezza2(A, L, livello=0)
    return max(L)


"""
Esercizio 5.

Scrivere un metodo ricorsivo speculari(A,B) che, dati due alberi binari A e B in input restituisce 
True qualora esista un nodo di A il cui valore è contenuto in almeno due nodi di B. 
"""


def speculari(A, B):
    """
    Dati due alberi binari A e B in input, restituisce True qualora esista un
     nodo di A il cui valore è contenuto in almeno due nodi di B
    :param A:
    :param B:
    :return:
    """
    if empty(A) or empty(B):
        return False
    if almeno2(B, value(A), 0):
        return True
    return speculari(left(A), B) or speculari(right(A), B)


def almeno2(B, info, cont):
    if empty(B):
        return False
    if value(B) == info:
        cont += 1
        if cont == 2:
            return True
    return almeno2(left(B), info, cont) or almeno2(right(B), info, cont)


if __name__ == '__main__':

    C = [8, 3, 1, 6, 4, 7, 10, 14, 13]

    A = createTree()

    for n in C:
        insert(A, n)

    xs = somma(A)
    print(f'Somma delle informazioni albero A : {xs}')
    xf = sommaFoglie(A)
    print(f'Somma delle foglie albero A : {xf}')

    X1 = [5, 9, 4, 3, 6, 12]
    X2 = [50, 32, 70, 29, 44, 90, 55]
    A1, A2 = createTree(), createTree()

    for x in X1:
        insert(A1, x)
    for x in X2:
        insert(A2, x)

    concatenazione(A1, A2)
    print()
    print('Concatenazione di A1 e A2 : ', A1)
    print()
    x = verificaABR(A)
    print(f'Verifica ABR A : {x}')
    print()
    print(f'Larghezza A : {larghezza(A)}')

    print()
    print(f'Speculari A, A1 : {speculari(A, A1)}')



