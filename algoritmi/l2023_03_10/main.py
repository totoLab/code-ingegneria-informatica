# risoluzione sistema lineare con il metodo di Cramer

def sistema(A, b):
    d = det(A)
    X = [] # vettore delle soluzioni

    for i in range(len(A)):
        replace(A, b, i)
        X.append(det(A)/d)
        replace(A, b, i)
    return X

def replace(A, B, k):
    for i in range(len(A)):
        A[i][k], B[i] = B[i], A[i][k]

def det(A):
    if len(A) == 1:
        return A[0][0]
    
    d = .0
    for j in range(len(A)):
        C = riduci(A, j)
        d += A[0][j] * pow(-1, j) * det(C)

    return d

def riduci(A, k):
    C = []
    for i in range(1, len(A)):
        C.append(riduciV(A[i], k))
    return C

def riduciV(V, k):
    U = []
    for i in range(len(V)):
        if i != k:
            U.append(V[i])
    return U

def stampaMatrice(A):
    s = ""
    for i in range(len(A)):
        s += f"{A[i]}\n"
    return s

M = [
    [1, -4, 2],
    [3, 1, -6],
    [1, -1, 1]
]
b = [1, 1, 1]
print(f"{stampaMatrice(M)}det = {det(M)}")
print(f"Soluzioni: {sistema(M, b)}")