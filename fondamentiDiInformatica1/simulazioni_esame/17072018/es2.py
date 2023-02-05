def esamina_lista(L):
    for x in range(len(L)//2):
        simmetrico = len(L) - x - 1
        somma_x = L[x] + L[simmetrico]
        somma_sottolista = 0
        for i in range(x+1, simmetrico-1):
            somma_sottolista += L[i]

        if somma_sottolista >= somma_x:
            return False

    return True

L = [12,9,7,2,1,1,3,12]
print(esamina_lista(L))