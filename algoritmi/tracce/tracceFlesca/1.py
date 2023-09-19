def verifica(T):
    if value(T) < 0:
        if isLeaf(T):
            return True
        
        if value(T) == 0:
            return False
    
    return verifica(left(T)) or verifica(right(T))