def prim(G, s):
    n = g.size(G)
    pred    = [ -1 for i in range(n) ]
    visited = [ False for i in range(n) ] 
    dist    = [ math.inf for i in range(n) ]
    dist[s] = 0
    Edges = []

    Q = h.createHeap()
    h.insertHeap(Q, [s, 0])

    while not h.empty(Q): 
        [next, _] = h.deleteMin(Q) 
        if not visited[next]:      
            visited[next] = True   
            Edges.append([pred[next], next, dist[next]])
            Adj = g.neighbours(G, i) 
            for [y, w] in Adj:       
                if not visited[y]:   
                    d = w # unica differenza con algoritmo di Dijkstra
                    if d < dist[y]:
                        dist[y] = d
                        pred[y] = next
                        h.insertHeap(Q, [y, d]) 
    Edges.pop(0)
    return Edges