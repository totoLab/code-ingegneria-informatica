# trovare la distanza dalla radice della foglia più vicina

def foglia_vicina(A):
    d = 0
    foglia_vicinaR(A)

def foglia_vicinaR(A):
    if empty(A):
        return 0
    elif isLeaf(A):
        return 1
    else:
        return 1 + min( foglia_vicinaR(left(A)), foglia_vicinaR(right(A)) )