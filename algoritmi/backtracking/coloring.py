# pseudo codice
import graphM as g

def mapColoring(G, node, colors, coloring):
    if node not in g.nodes(G):
        return True

    for c in colors:
        if check(G, node, c, coloring):
            coloring[node] = c
            if mapColoring(G, g.next_node(G, node), colors, coloring):
                return True
            else:
                coloring[node] = "   "
    return False

def check(G, node, color, coloring):
    Adj = g.neighbours(G, node)
    for [y, w] in Adj:
        if coloring[y] == color:
            return False
    return True