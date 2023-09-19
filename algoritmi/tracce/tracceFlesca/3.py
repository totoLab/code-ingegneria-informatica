# implementare un metodo ricorsivo verifica(T) che resituisce True se e solo se esiste un nodo non foglia n di T
# tale che tutti i nodi foglia che appaiono nel sottoalbero radicato in n hanno lo stesso valore

leafValue = -1

def checkLeaf(T): 
    global leafValue
    if leafValue != -1:
        leafValue = value(T)
        return True
    else:
        return value(T) == leafValue

def verifica(T):
    if empty(T): return False
    if isLeaf(T): return checkLeaf(T)

    left, right = left(T), right(T)
    if isLeaf(left) and checkLeaf(left):
        verificaLeft = verifica(left)
    else:
        return False
    
    if isLeaf(right) and checkLeaf(right):
        verificaRight= verifica(right)
    else:
        return False

    return verificaLeft and verificaRight