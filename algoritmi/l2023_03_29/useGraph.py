import graphL as g

G = g.createGraph(5)
g.printGraph(G)
print()

C = [
    [0, 1], [0, 4], [1, 2], [2, 3], [3, 1], [4, 3]
]

for [x, y] in C:
    g.insertEdge(G, x, y)

g.printGraph(G)
print()

D = [
    [1, 2], [3, 1], [4, 0]
]

for [x, y] in D:
    g.deleteEdge(G, x, y)

g.printGraph(G)
print()

L = g.neighbours(G, 0)
print(L)