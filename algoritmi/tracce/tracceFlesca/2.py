# implementa un metodo ricorsivo verifica(T) che restituisce True se e solo se esiste un nodo n di T
# tale che tutti i nodi del sottoalbero con radice n abbiano lo stesso valore di n

def verifica(T):
    if not isLeaf(T):
        if verificaSottoAlbero(T, value(T)):
            return True 

    return verifica(left(t)) or verifica(right(T)) 

def verificaSottoAlbero(T, value):
    if empty(T):
        return True
    if value(T) != value:
        return False
    return verificaSottoAlbero(left(T), value) and verificaSottoAlbero(right(T), value)