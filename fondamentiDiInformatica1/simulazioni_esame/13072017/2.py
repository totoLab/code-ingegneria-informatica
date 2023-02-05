def verifica_sequenza(L, k):
    for i in range(len(L) - k):
        if somma_sequenza(L, i, k) == 0:
            return True
    return False

def somma_sequenza(L, i, k):
    somma = 0
    for i in range(i, i+k):
        somma += L[i]

    return somma

print(verifica_sequenza([6, 1,-5, 3, 1, -5, -1, 9], 4))