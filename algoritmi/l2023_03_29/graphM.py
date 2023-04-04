# implementazione con matrice di adiacenza

def createGraph(n): # Theta(n^2)
    return [[0] * n] * n

def copyGraph(G):
    C = []
    for i in range(G):
        C.append([])
        for j in G[i]:
            C[i].append(j)
    return C

def size(G):
    return len(G)

def nodes(G):
    C = []
    for i in range(size(G)):
        C.append(i)
    return C

def insertEdge(G, i, j): # Theta(1)
    G[i][j] = 1

def deleteEdge(G, i, j): # Theta(1)
    G[i][j] = 0

def isEdge(G, x, y): # Theta(1)
    return G[x][y] == 1

def neighbours(G, i): # O(n)
    C = []
    for j in range(len(G)): # per ipotesi la matrice è quadrata
        if G[i][j] == 1:
            C.append(G[i][j])
    return C

def printGraph(G):
    for i in range(size(G)):
        print(G[i])