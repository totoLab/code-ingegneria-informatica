from l2023_04_04 import WGraphL as g
import math

C = [ 
    [0, 1, 10], [0, 2, 5], [1, 2, 2], [1, 3, 1], [2, 1, 3], [2, 3, 9], [2, 4, 2], [3, 4, 4], [4, 0, 7], [4, 3, 6]
]
G = g.createGraph(5)
for [x, y, w] in C:
    g.insertEdge(G, x, y, w)


g.printGraph(G)

def Dijkstra(G, s):
    n = g.size(G)
    pred    = [ -1 for i in range(n) ]
    visited = [ False for i in range(n) ] 
    dist    = [ math.inf for i in range(n) ]
    dist[s] = 0
    Edges = []

    for i in range(n):
        next = minVertex(dist, visited)
        visited[next] = True
        Edges.append([pred[next], next, dist[next]])
        Adj = g.neighbours(G, i)
        for [y, w] in Adj:
            if not visited[y]:
                d = dist[next] + g.weight(G, next, y)
                if d < dist[y]:
                    dist[y] = d
                    pred[y] = next
        Edges.pop(0)
        return Edges

def minVertex(dist, visited):
    minVertexindex = -1
    minDist = math.inf
    for i in range(len(visited)):
        if not visited[i] and dist[i] < minDist:
            minDist = dist[i]
            minVertexindex = i
    return minVertexindex