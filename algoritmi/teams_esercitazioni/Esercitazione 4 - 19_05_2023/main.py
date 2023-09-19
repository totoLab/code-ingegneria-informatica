# Input = sequenza di matrici compatibili, es P = [[p0,p1], [p1,p2],.....]


def compatibili(P):
    for i in range(len(P)-1):
        if P[i][1] != P[i+1][0]:
            return False
    return True

def stampaMatrice(A):
    for i in range(len(A)):
        for j in range(len(A[i])):
            print(f'{A[i][j]}', end=' ')
        print()

def creaMatrice(r, c):
    M = []
    for i in range(r):
        M.append([])
        for j in range(c):
            M[i].append(0)
    return M


def sequenzaMatrici(P):
    """

    :param P:  sequenza di matrici a 2 a 2 compatibili
    :return: C: matrice dei costi ottimi, S: matrice dei k otitmi
    """
    if not compatibili(P):
        return None

    n = len(P)  # numero di matrici della sequenza
    C = creaMatrice(n, n)  # matrice dei costi ottimi
    S = creaMatrice(n, n) # matrice dei k ottimi

    for i in range(n):
        C[i][i] = 0  # sottoproblemi di taglia 1

    for l in range(1, n):
        for i in range(n-l):
            j = i + l  # j - i = l
            C[i][j] = float('inf')
            for k in range(i, j):
                q = C[i][k] + C[k+1][j] + P[i][0] * P[k][1]*P[j][1]
                if q < C[i][j]:
                    C[i][j] = q
                    S[i][j] = k
    return C, S

# T(n) = O(n^3)
# S(n) = O(n^2)


def parentesizzazione(S, i, j):

    if i == j:
        print(f'A{i}', end=' ')  # caso base

    else:
        print(' ( ', end=' ')
        k = S[i][j]
        parentesizzazione(S, i, k)
        print(' * ', end=' ')
        parentesizzazione(S, k+1, j)
        print(' ) ', end=' ')


if __name__ == '__main__':

    P1 = [[30, 35], [35, 15], [15, 5], [5, 10], [10, 20], [20, 25]]
    n = len(P1)
    C, S = sequenzaMatrici(P1)
    print('Matrice dei costi ottimi : ')
    stampaMatrice(C)
    print('Matrici dei k ottimi : ')
    stampaMatrice(S)
    print()
    print('Costo ottimo : ', C[0][n-1])
    print()
    parentesizzazione(S, 0, len(S)-1)