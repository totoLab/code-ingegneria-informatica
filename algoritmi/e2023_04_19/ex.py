# es1: restituisce l'id di tutti gli utenti con almeno k amici connessi con forza maggiore o uguale ad h
from graph import Graph

def strength(G, k, h): # O(n + 2m), ma n < m asintoticamente => O(m)
    ret = []
    nodes = G.get_nodes()

    for n in nodes: # O(n)
        if G.degree(n) >= k:
            neighbours = G.get_neighbours(n) # grado(n)
            count = 0
            for [_, w] in neighbours: # O(2m)
                if w >= h:
                    count += 1
        if count >= h:
            ret.append(n)
    return ret

# es2: calcolo nodo più popolare nel grafo, con popolarità calcolata come numero di nodi a distanza max = 2, contati una sola volta

def nodePopularity(G, x): # O(m)
    adj = G.neighbours(x)
    visited = {}
    visited[x] = True
    count = 0
    for [j, _] in adj:
        if j not in visited or not visited[j]:
            visited[j] = True
            count += 1
        amici2 = G.neighbours(j)
        for [h, _] in amici2:
            if h not in visited or not visited[h]:
                visited[h] = True
                count += 1
    return count

def popular(G): # O(n * m) = O(m)
    nodes = G.nodes()
    v_max = -1
    n_max = -1
    for n in nodes:
        pi = nodePopularity(G, node)
        if pi > v_max:
            v_max = pi
            n_max = n
    return n_max