# 1. tutti i nodi al livello pari devono avere valore minore del massimo del sottoalbero destro della radice

def verifica(A):
    if empty(A):
        return True
    maxn = massimo(right(A))
    return livello(A, maxr, 0)

def massimo(A):
    if empty(A):
        return float("-inf")
    return max(
        t.value(A), massimo(t.left(A)), massimo(t.right(A))
    ) 

def livello(A, s, l):
    if t.empty(A) or t.isLeaf(A):
        return True
    if l % 2 == 0:
        if t.value(s) < s:
            return False
    return livello(left(A), s, l + 1) and livello(right(A), s, l + 1)

# 2. complessità caso peggiore Quicksort: https://en.wikipedia.org/wiki/Randomized_algorithm