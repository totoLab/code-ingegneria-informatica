def lettere_comuni(L):
    lettere_possibili = list(L[0])
    for stringa in L:
        for lettera in lettere_possibili:
            if lettera not in stringa:
                indice = lettere_possibili.index(lettera)
                lettere_possibili.pop(indice)            
    return lettere_possibili

print(lettere_comuni(['abc', 'bdecf', 'cabe', 'bcfeg']))