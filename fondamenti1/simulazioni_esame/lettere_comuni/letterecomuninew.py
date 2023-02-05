def lettere_comuni(L, k):
    lettere_possibili=list(L[0])
    i = 0
    while i < len(lettere_possibili):
        for stringa in L:
            if lettere_possibili[i] not in stringa:
                lettere_possibili.pop(i)

        i+=1

    if len(lettere_possibili) < k:
        return []
    else:
        return lettere_possibili
