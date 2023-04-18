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

