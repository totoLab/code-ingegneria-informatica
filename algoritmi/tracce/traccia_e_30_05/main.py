
def nodo1figlio(T):
    return nodo1figlioR(T, False)

def nodo1figlioR(T, dispari): # implementazione prof
    if t.empty(T) or t.isLeaf(T):
        return False

    if dispari:
        if not t.empty(left(T)) and not t.empty(right(T)):
            return False
        return True
    return nodo1figlioR(t.left(T), not dispari) or nodo1figlioR(t.right(T), not dispari)  

def nodo1figlioR(T, dispari):
    if dispari:
        if not t.empty(left(T)):
            if not t.empty(right(T)):
                return False
        if not t.empty(right(T)):
            if not t.empty(left(T)):
                return False
        return True
    return nodo1figlioR(t.left(T), not dispari) or nodo1figlioR(t.right(T), not dispari)

# complessità temporale nel caso peggiore: visita di tutti i nodi -> \theta(n)
# complessità spaziale nel caso peggiore: O(h) nel caso degenere nodi a livelli dispari con 2 figli e nodi pari con 1 figlio
#                                      -> n = 1 + h/2 + h -> h = \theta(n)