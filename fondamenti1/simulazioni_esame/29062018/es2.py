def esamina_lista(L, t):
    for x in L:
        if x > t:
            return 0
    
    k = len(L)
    ok = True
    while k > 0 and ok:
        for i in range(0,len(L) - k):
            sottolista = L[i:i+k+1]
            if sum(sottolista) > t:
                ok = False
        k -= 1
    
    return k

L = [3,0,7,2,0]
t = 10
print(esamina_lista(L, t))

