#2 
def verifica_liste(L1,L2):
    for x in L1:
        ret = False
        for i in range(len(L2)):
            if L2[i] == x:
                cont = 0
                j = i
                while j < i+x and j != len(L2) - 1:
                    if L2[j] == x:
                        cont += 1

                    j += 1

                if cont == x:
                    ret = True

    return ret

print(verifica_liste([3,1], [-3,2,1,5,4,4,1,4,-1,2,3,3,3,3]))