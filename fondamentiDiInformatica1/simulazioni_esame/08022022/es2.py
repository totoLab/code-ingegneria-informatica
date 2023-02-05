
def verifica_lista(L, k):
    for i in range(len(L)-k):
        sottolista = L[i:i+k+1] # sottolista di elemento corrente + k elementi successivi
        primo = sottolista[0]   # elemento corrente (primo della sottolista)
        for j in range(1, len(sottolista)):     # per ogni elemento tranne il primo
            elemento = sottolista[j]
            if lettere_comuni(primo, elemento): # se ci sono lettere in comune dai False
                return False

    return True

# restituisce True se le stringhe hanno almeno una lettera in comune
def lettere_comuni(str1, str2): 
    for lettera in str1:
        if lettera in str2:
            return True

    return False


L = ["abcd", "efgh", "ijk", "ab", "cdef"]
res = verifica_lista(L, 2)
print(res)