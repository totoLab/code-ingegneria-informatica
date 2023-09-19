def riduciV(V, k):
    """
    Calcola un sottovettore di v, rimuovendo l'elemento in posizione k
    :param V: vettore
    :param k: indice dell'elemento da rimuovere
    :return: Vettore di dimensione n-1
    """
    U = []  # Theta(1)
    for i in range(0, len(V)):
        if i != k:  # \Theta(1) * n
            U.append(V[i])  #Theta(1) * n
    return U


# Sia n la dimensione di v (|V|)
# T(n) = Theta(n)
# S(n) = Theta(n)


def riduci(A, k):
    """
    Calcola una sottomatrice di A rimuovendo la riga 0 e la colonna k
    :param A: Matrice A di dimensione (n, n)
    :param k: colonna da rimuovere
    :return: Sottomatrice di dimensione (n-1, n-1)
    """
    C = []
    for i in range(1, len(A)):
        C.append(riduciV(A[i], k))  # Theta(n) * n = Theta(n^2)
    return C

# Sia n l'ordine della matrice.
# T(n) = Theta(n^2)
# S(n) = Theta(n^2)


def det(A):
    """
    Calcola il determinante mediante lo sviluppo di Laplace
    :param A: Matrice quadrata di ordine n
    :return: determinante
    """

    if len(A) == 1: # Theta(1)
        return A[0][0] # Theta(1)
    d = .0  # Theta(1)
    for j in range(len(A)):  # n * Theta(1)
        C = riduci(A, j)  # n * Theta(n^2)
        d = d + A[0][j] * pow(-1, j) * det(C)  # n*(Theta(1) + T(n-1))
    return d


def stampaMatrice(A):
    for i in range(len(A)):
        for j in range(len(A[i])):
            print(A[i][j], end=' ')
        print()


M = [[1, -4, 2],
     [3, 1, -6],
     [1, -1, 1]]

stampaMatrice(M)
k = 1
print(f'Sottomatrice rimuovendo riga 0 e colonna {k} :  {riduci(M, k)}')
print(f'Determinante M : {det(M)}')


def replace(A, B, k):
    for i in range(len(A)):
        A[i][k], B[i] = B[i], A[i][k] # Theta(1) * n


def sistema(A, B):
    d = det(A) # Theta(n!)
    X = []  # Theta(1)
    for i in range(len(A)):
        replace(A, B, i)  # Theta(n)
        X.append(det(A)/d) # Theta(n!)
        replace(A, B, i)  # Theta(n)
    return X

#T(n) = Theta(n!)
#S(n) = Theta(n^3)


A = [[1, -4, 2], [3, 1, -6], [1, -1, -1]]
B = [9, 1, 3]

print('Matrice dei coefficienti ', stampaMatrice(A))
print()
print('Vettore termini noti : ', B)
print()
print(sistema(A, B))