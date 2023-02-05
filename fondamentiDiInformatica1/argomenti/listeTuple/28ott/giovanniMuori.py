import generate as gen
def controlla_lista(lista):
    maxNum = max(lista)
    minNum = min(lista)
    if maxNum == minNum:
        return maxNum
    else:
        indexMax = lista.index(maxNum)
        indexMin = lista.index(minNum)
        return maxNum, indexMax, minNum, indexMin

length = int(input("Insert list length: "))
lista = gen.list_gen(length)
print(controlla_lista(lista))