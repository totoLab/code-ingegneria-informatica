import math

def createGraph(n):
    return [ [] for i in range(n) ]

def copyGraph(G):
    C = createGraph(len(G))
    for i in range(len(G)):
        for [j, w] in G[i]:
            C.append([j, w])
    return C

def size(G):
    return len(G)

def printGraph(G):
    for i in range(len(G)):
        print(f"[{i}]: {G[i]}")

def nodes(G):
    n = len(G)
    return [ i for i in range(n) ]

def isEdge(G, i, j):
    for [k, w] in G[i]:
        if k == j:
            return True
    return False

def weight(G, i, j):
    for [k, w] in G[i]:
        if k == j:
            return w
    return math.inf

def insertEdge(G, i, j, w):
    if isEdge(G, i, j):
        deleteEdge(G, i, j)
    G[i].append([j, w])

def deleteEdge(G, i, j):
    m = 0
    for [k, w] in G[i]:
        if k == j:
            G[i].pop(m)
        m += 1

def neighbours(G, i):
    C = []
    for [j, w] in G[i]:
        C.append([j, w])
    return C
