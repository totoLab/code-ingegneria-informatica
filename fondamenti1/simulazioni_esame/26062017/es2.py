def azzera_duplicati_non_funziona(L1):
    L2 = [L1[0]]
    for i in range(1, len(L1)):
        ok = True
        for j in range(i):
            if L1[i] != L1[j]:
                ok = False
        if ok:
            L2.append(L1[i])
        else:
            L2.append(0)

    return L2

def azzera_duplicati(L1):
    L2 = []
    for x in L1:
        if x not in L2:
            L2.append(x)
        else:
            L2.append(0)
    return L2

L1 = [2,1,5,2,7,5,1,9]
print(azzera_duplicati(L1))