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

Ws = [2, 3, 4, 6]
Vs = [1, 3, 7, 9]
Cs = [9, 5, 4, 4]
b = 50
print(f">>> {Ws=}, {Vs=}, {Cs=}, {b=}")
[weight, value, sack] = KnapsackF(b, Ws, Vs, Cs)
print(f"{weight=}, {value=}, {sack=}")