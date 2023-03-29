# implementazione con matrice di adiacenza

def createGraph(n): # Theta(n^2)
    return [[0] * n] * n

def addEdge(G, x, y): # Theta(1)
    G[x][y] = 1

def deleteEdge(G, x, y): # Theta(1)
    G[x][y] = 0

def isEdge(G, x, y): # Theta(1)
    return G[x][y] == 1

def neighbours(G, i): # O(n)
    C = []
    for j in range(len(G)): # per ipotesi la matrice è quadrata
        if G[i][j] == 1:
            C.append(G[i][j])
    return C