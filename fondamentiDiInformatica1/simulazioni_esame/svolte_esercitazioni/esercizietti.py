def pari_dispari():
    numero = int(input("Inserisci numero: "))
    if numero % 2 == 0:
        print(numero, "è pari")
    else:
        print(numero, "è dispari")

def anno_bisestile():
    anno = int(input("inserisci anno: "))
    if anno % 4 == 0 and anno % 100 != 0 or anno % 400 == 0:
        print(anno, "è bisestile")
    else:
        print(anno, "non è bisestile")

def potenza(base, exp):
    prodotto = base
    for i in range(exp - 1):
        prodotto *= base
    
    print(prodotto)

def rollup(lista):
    ret = []
    for i in range(0, len(lista), 2):
        ret.append(lista[i] + lista[i + 1])

    return ret

def alternati(lista):
    continua = True
    for i in range(len(lista) - 1):
        pari_e_dispari = lista[i] % 2 == 0 and lista[i + 1] % 2 != 0
        dispari_e_pari = lista[i + 1] % 2 == 0 and lista[i] % 2 != 0
        if not(pari_e_dispari or dispari_e_pari):
            continua = False

        if not continua:
            return False

    return True

def massimo_indice(lista, indice_vecchio, indice_nuovo):
    massimo = vecchio = lista[indice_vecchio]
    nuovo = lista[indice_nuovo]
    indice_massimo = indice_vecchio
    if nuovo > vecchio:
        massimo = nuovo
        indice_massimo = indice_nuovo

    return massimo, indice_massimo

def minimo_indice(lista, indice_vecchio, indice_nuovo):
    minimo = vecchio = lista[indice_vecchio]
    nuovo = lista[indice_nuovo]
    indice_minimo = indice_vecchio
    if nuovo < vecchio:
        minimo = nuovo
        indice_minimo = indice_nuovo

    return minimo, indice_minimo

def controlla_lista(lista):
    valore = lista[0]
    minimo = massimo = valore
    indice_minimo = indice_massimo = 0
    for i in range(len(lista)):
        massimo, indice_massimo = massimo_indice(lista, indice_massimo, i)
        minimo, indice_minimo = minimo_indice(lista, indice_minimo, i)
    
    if massimo == minimo:
        return valore
    else:
        return f"Massimo: {massimo} indice {indice_massimo}; minimo: {minimo} indice {indice_minimo}"

        
#pari_dispari()
#anno_bisestile()
#potenza(2, 8)
#print(rollup([1,3,6,1,6,3,8,9]))
#m = [
#    [2,5,8,3,2],
#    [1,2,3,4,0],
#    [1,1,2,3,4,5]
#]
#for lista in m:
#    print(alternati(lista))

#m = [
#    [1, 1, 1, 1, 1],
#    [4],
#    [2, 6, 1, 7, 1, 9, 6],
#    [9, 1, 1, 1, 1],
#]
#for i, lista in enumerate(m):
#    print(f"lista{i + 1}: {controlla_lista(lista)}")
