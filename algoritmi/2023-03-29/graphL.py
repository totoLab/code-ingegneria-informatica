# implementazione con lista di adiacenza

def createGraph(n):  # Theta(n)
    return [[]] * n

def isEdge(G, i, j): # O(n)
    return j in G[i]

def addEdge(G, i, j): # Theta(1)
    if not isEdge(G, i, j):
        G[i].append(j)

def deleteEdge(G, i, j): # O(n)
    return G[i]

def neighbours(G, i): # O(n)
    return G[i][:]