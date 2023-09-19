
def stampaMatrice(A):
    for i in range(len(A)):
        for j in range(len(A[i])):
            print(f'{A[i][j]}', end=' ')
        print()


def trova(A, x):
    return trova2(A, x, 0, 0)


def trova2(A, x, i, j):
    if i >= len(A) or j >= len(A[0]):
        return False
    return A[i][j] == x or trova2(A, x, i, j+1) or trova2(A, x, i+1, 0)


A = [[1, -4, 2], [3, 1, -6], [1, -1, -1]]
x = 120

stampaMatrice(A)
print(trova(A, x))

# Sia n il numero di righe della matrice e m il numero di colonne
# Complessità nel caso peggiore:
# T(n) = Theta(n*m)
# S(n) = Theta(n*m)