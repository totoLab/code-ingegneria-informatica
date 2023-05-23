# Restituisce true
#  -  se e solo se per ogni nodo non foglia x dell'albero a vale che x.val è pari alla somma dei valori
#     dei nodi contenuti nel sottoalbero sx di x
import tree as t

def entrypoint(A):
    return flow(A)

def flow(A):
    if not t.isLeaf(A):
        somma = sommaAlbero(left(A))
        if somma != t.value(A):
            return False
        return flow(left(A)) and flow(right(A))
    return True

def sommaAlbero(A):
    if empty(A):
        return 0
    if t.isLeaf(A):
        return value(A)
    return sommaAlbero(left(A)) + sommaAlbero(right(A)) 