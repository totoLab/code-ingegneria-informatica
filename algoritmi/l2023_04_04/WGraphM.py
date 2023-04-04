import math

def createGraph(n):
    return [ [ math.inf for j in range(n)] for i in range(n) ]

def copyGraph(G):
    n = len(G)
    return [ [ G[i][j] for j in range(n)] for i in range(n) ]

def size(G):
    return len(G)

def printGraph(G):
    for i in range(len(G)):
        print(G[i])

def nodes(G):
    n = len(G)
    return [ i for i in range(n) ]

def isEdge(G, i, j):
    return G[i][j] != math.inf

def weight(G, i, j):
    return G[i][j]

def insertEdge(G, i, j, w):
    G[i][j] = w # overwrite

def deleteEdge(G, i, j):
    G[i][j] = math.inf

def neighbours(G, i):
    return [ 
        [ j, G[i][j] ]
        for j in range(len(G))
        if isEdge(G, i, j)
    ] 
