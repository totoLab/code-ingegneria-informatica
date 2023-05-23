import graphM as g

def hamilton(G, source, node, count, visited, pred): # O(n^n)
    if count == g.size(G) - 1 and g.isEdge(G, node, source):
        pred[source] = node
        return True
    
    Adj = g.neighbours(G, node)
    for [j, w] in Adj:
        if not visited[j]:
            visited[j] = True
            pred[j] = node
            if hamilton(G, source, j, count + 1, visited, pred):
                return True
            visited[j] = False
            pred[j] = -1
    return False