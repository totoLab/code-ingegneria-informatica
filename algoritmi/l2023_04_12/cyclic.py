from l2023_04_04 import WGraphM as g

def cyclicPath(G, x, CPath, Reached):
    CPath[x] = True
    Adj = g.neighbours(G, x)
    for [y, w] in Adj:
        if not Reached[y]:
            if (CPath[y]):
                return True
            elif cyclicPath(G, x, CPath, Reached):
                return True
    CPath[x] = False
    Reached[x] = True
    return False

def Cyclic(G):
    CPath = [ False for i in range(len(G)) ]
    Reached =  [ False for i in range(len(G)) ]
    for x in g.nodes(G):
        if not Reached[x] and cyclicPath(G, x, CPath, Reached):
            return True
    return False
