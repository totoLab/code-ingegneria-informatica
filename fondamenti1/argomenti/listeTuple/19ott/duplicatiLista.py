def contiene_duplicati_nested_for(lista): # inefficente
    for i in range(len(lista)):
        for j in range(len(lista)):
            if i != j and lista[i] == lista[j]:
                return True
    
    return False

def contiene_duplicati(lista):
    for i in range(len(lista) - 1):
        for j in range(i + 1, len(lista)):
            if lista[i] == lista[j]:
                return True
    
    return False

lista = [1, 2, 4, 4, 1]
print(contiene_duplicati(lista))