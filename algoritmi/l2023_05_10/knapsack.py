# versione frazionaria
def KnapsackF(b, Ws, Vs, Cs): # costo algoritmo = costo del sorting
    n = len(Ws)
    D = [ [ Vs[i] / Ws[i], i ] for i in range(n)] # vettore dei rapporti
    D.sort()
    D.reverse() # ordinato in modo decrescente

    weight = 0
    value = 0
    sack = []
    i = 0
    while weight < b and i < n:
        j = D[i][1] # index elemento
        k = min( (b - weight) / Ws[j], Cs[j] )
        weight += k * Ws[j]
        value += k * Vs[j]
        sack.append([j, k])
        i += 1
    return [weight, value, sack]

def KnapsackRec(b, Ws, Vs, n):
    if b == 0 or n < 0:
        return 0
    elif Ws[n] > b:
        return KnapsackRec(b, Ws, Vs, n - 1)
    else:
        return max(
            KnapsackRec(b, Ws, Vs, n - 1),
            KnapsackRec(b - Ws[n], Ws, Vs, n - 1) + Vs[n]
        )
     
def Selected01(b, Ws, Vs, Val):
    S = [ 0 for i in range(len(Ws)) ]
    j = b
    i = len(Ws) - 1
    while i >= 0: 
        if j >= Ws[i] and Val[j] - Val[j - Ws[i]] == Vs[i]:
            S[i] = 1
            j -= Ws[i]
        i -= 1
    return S

def Knapsack01it1(b, Ws, Vs):
    n = len(Ws) + 1
    M = [ [ 0 for n in range(b + 1) ] for i in range(n) ]

    for i in range(1, n):
        for j in range(1, Ws[i - 1]): # elemento non inseribile
            M[i][j] = M[i - 1][j]
        for j in range(b, Ws[i - 1] - 1, -1):
            M[i][j] = max(
                M[i - 1][j],
                M[i - 1][j - Ws[i - 1]] + Vs[i - 1]
            )
    return [ M[n - 1][b], Selected01(b, Ws, Vs, M[n - 1]) ]

def main(v):
    if v == 0:
        Ws = [2, 3, 4, 6]
        Vs = [1, 3, 7, 9]
        Cs = [9, 5, 4, 4]
        b = 50
        print(f"Knapsack frazionario {Ws=}, {Vs=}, {Cs=}, {b=}")
        [weight, value, sack] = KnapsackF(b, Ws, Vs, Cs)
        print(f"{weight=}, {value=}, {sack=}")
    else:
        Ws = [2, 3, 4, 7]
        Vs = [1, 3, 7, 9]
        b = 11
        if v == 1:
            print(f"Knapsack 01 ricorsiva: {Ws=}, {Vs=}, {b=}")
            value = KnapsackRec(b, Ws, Vs, len(Ws) - 1)
            print(f"{value=}")
        elif v == 2:
            print(f"Knapsack 01 iterativa: {Ws=}, {Vs=}, {b=}")
            [value, sack] = Knapsack01it1(b, Ws, Vs)
            print(f"{value=}, {sack=}")


if __name__ == "__main__":
    main(2)