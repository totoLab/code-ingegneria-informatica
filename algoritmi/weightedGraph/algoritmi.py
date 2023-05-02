import WGraphM as g

def closure(G):
    # rimodulazione peso archi a 1
    C = g.createGraph(g.size(G))
    for i in g.nodes(G):
        Adj = g.neighbours(G, i)
        for [j, w] in Adj:
            g.insertEdge(C, i, j, 1)

    # individuazione archi condivisi per aggiungere nuovo arco alla closure
    for k in g.nodes(C):
        for i in g.nodes(C):
            for j in g.nodes(C):
                if g.isEdge(C, i, k) and g.isEdge(C, k, j):
                    g.insertEdge(C, i, k, 1)
    
    return C

def Kruskal(G):
    V = []
    for i in g.nodes(G): # O(m) perché i cicli non sono ind. e al massimo vengono visti tutti gli archi
        Adj = g.neighbours(G, i)
        for [j, w] in Adj:
            V.append([i, j], w) # arco generico
    B = h.heapSort(V) # archi ordinati per peso -> O(m log2 m) = O(m log2 n^2) = O(m log2 n)
    C = [] # cluster
    for i in g.nodes(G):
        C.append([i])
    T = [] # albero ricoprente
    for [[i, j], w] in B: #O(m)
        if C[i] != C[j]: # per cluster diversi
            T.append([[i, j], w])

            if len(C[i]) < len(C[j]): # cambio etichetta ai nodi del cluster più piccolo -> O(n log2 n)
                for k in C[i]:
                    C[j].append(k)
                    C[i] = C[j]
            else:
                for k in C[j]:
                    C[i].append(k)
                    C[j] = C[i]
                    
