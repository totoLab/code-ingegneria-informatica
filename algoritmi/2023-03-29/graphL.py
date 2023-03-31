# implementazione con lista di adiacenza

def createGraph(n):  # Theta(n)
    return [[]] * n

def copyGraph(G):   # Theta(m + n) = Theta(m) 
    C = createGraph(size(G))
    for i in range(size(G)):
        for k in G[i]:
            C[i].append(k)
    return C

def size(G):
    return len(G)

def nodes(G): # non necessaria, i nodi sono uno per ogni indice della lista di adiacenza
    C = []
    for i in range(size(G)):
        C.append(i)
    return C

def isEdge(G, i, j): # O(n)
    return j in G[i]

def insertEdge(G, i, j): # Theta(1)
    if not isEdge(G, i, j):
        G[i].append(j)

def deleteEdge(G, i, j): # Theta(n)
    G[i].remove(j)

def neighbours(G, i): # O(n)
    return G[i][:] # entire list's copy