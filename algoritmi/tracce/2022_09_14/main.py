import tree as t

def verificaFigli(T):
    return verificaFigliR(T, 0)

def verificaFigliR(T, distance):
    if distance % 2 == 0: 
        if t.empty(t.left(A)):
            return t.empty(t.right(A))

        # condizione equivalente più esplicita
        # if t.isLeaf(T):
        #     return True
        # if empty(t.left(A)) or empty(t.right(A)):
        #     return False

    return verificaFigliR(t.left(T), distance + 1) and verificaFigliR(t.right(T), distance + 1)