# prodotto di sequenza di matrici compatibili a coppie
# l'obiettivo è minimizzare il numero di prodotti scalari

def printMatrix(M, name):
    print(f"{name}:")
    for row in M:
        print(row)
    print()

def compatibili(P):
    for i in range(len(P) - 1):
        if P[i][1] != P[i+1][0]:
            return False
    return True

def creaMatrice(r, c):
    M = []
    for i in range(r):
        M.append([])
        for j in range(c):
            M[i].append(0)
    return M

# T(n) = O(n^3), S(n) = O(n^2)
def sequenzaMatrici(P):
    if not compatibili(P):
        return None

    n = len(P) # numero matrici sequenza
    C = creaMatrice(n, n)
    S = creaMatrice(n, n)

    for i in range(n):
        C[i][i] = 0 # sottoproblemi di taglia 1
    
    # doppio loop sopra la diagonale principale
    for l in range(1, n):
        for i in range(n - l):
            j = i + l
            C[i][j] = float("inf")
            for k in range(i, j):
                # formula di costo
                q = C[i][k] + C[k + 1][j] + ( P[i][0] * P[k][1] * P[j][1] )
                if q < C[i][j]:
                    C[i][j] = q
                    S[i][j] = k
    
    return C, S

# O(log2 n)
def parentesizzazione(S, i, j):
    if i == j:
        print(f"A{i}", end='')
    else:
        print(" ( ", end="")
        k = S[i][j]
        parentesizzazione(S, i, k)
        print(" * ", end="")
        parentesizzazione(S, k + 1, j)
        print(" ) ", end="")

if __name__ == "__main__":
    P = [
        [50, 10], [10, 40], [40, 30], [30, 5]
    ]
    P = [
        [30, 35], [35, 15], [15, 5], [5, 10], [10, 20], [20, 25]
    ]

    C, S = sequenzaMatrici(P)
    printMatrix(C, "C")
    printMatrix(S, "S")
    parentesizzazione(S, 0, len(S) - 1)
    print()