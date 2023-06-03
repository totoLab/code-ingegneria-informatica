# scrivere un metodo che restituisce il nodo simmetrico del nodo x, dove il simmetrico di un nodo è definito come 
# il nodo che esiste alla posizione simmetrica del nodo x nel sottoalbero opposto alla radice.
# È possibile assumere che i valori dei nodi siano interi positivi e restituire -1 in caso di assenza del nodo.
import tree as t

def simmetrico(T, x):
    sequence = []
    if findNodeSequence(T, sequence, x):
        sequence.reverse()
        return verifySequence(T, sequence, x)
    return -1

def findNodeSequence(T, sequence, x):
    if t.empty(T):
        return False
    elif t.value(T) == x:
        return True
    elif findNodeSequence(t.left(T), sequence, x):
        sequence.append(0)
        return True
    elif findNodeSequence(t.right(T), sequence, x):
        sequence.append(1)
        return True
    return False

def verifySequence(T, sequence, x):
    current = T
    for descent in sequence:
        if t.empty(current):
            return -1
        elif descent == 0:
            current = t.right()
        elif descent == 1:
            current = t.left()

    return t.value(current) == x