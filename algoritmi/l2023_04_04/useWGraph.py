import WGraphL as g

G = g.createGraph(6)
L = [
    [0, 1, 2], [0, 5, 9], [1, 2, 6], [1, 3, 8],
    [1, 5, 5], [2, 3, 1], [4, 2, 7], [5, 4, 3]
]
for [i, j, w] in L:
    g.insertEdge(G, i, j, w)

print("Initial graph:")
g.printGraph(G)

g.deleteEdge(G, 4, 2)
print("Modified graph:")
g.printGraph(G)