def lettere_comuni(L):
    lettere_possibili=list(L[0])
    i = 0
    while i < len(lettere_possibili):
        for stringa in L:
            if lettere_possibili[i] not in stringa:
                lettere_possibili.pop(i)

        i+=1

    return lettere_possibili

print(lettere_comuni(['abc', 'bdecf', 'cabe', 'bcfeg']))