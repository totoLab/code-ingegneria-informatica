from l2023_04_04 import WGraphM as g

def edges(pred):
    E = []
    for i in range(len(pred)):
        if (pred[i] != -1):
            E.append(pred[i], i)
    return E

def breadthVisit(G, s):
    Queue = [s]
    Pred = [ -1 for i in range(len(G)) ]
    while (Queue != []):
        next = Queue.pop(0)
        Adj = g.neighbours(G, s)
        for [y, w] in Adj:
            if (Pred[y] == -1):
                Queue.append(y)
                Pred[y] = next
    return edges(Pred)

def depthVisit(G, s):
    Queue = [s]
    Pred = [ -1 for i in range(len(G)) ]
    while (Queue != []):
        next = Queue.pop()
        Adj = g.neighbours(G, s)
        for [y, w] in Adj:
            if (Pred[y] == -1):
                Queue.append(y)
                Pred[y] = next
    return edges(Pred)