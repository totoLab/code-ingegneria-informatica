import math

class Graph: # non orientato -> simmetrico (non c'è differenza tra archi entranti e uscenti) e pesato
    # spazio usato: O(n + 2m) per la lista di adiacenza

    class Node:
        
        def __init__(self, value):
            self._value = value
            self._neighbours = []

        def degree(self):
            return self.len(self._neighbours)

        def add_neighbour(self, x):
            self._neighbours.append(x)

        def add_neighbours(self, neighbours):
            for v in neighbours:
                add_neighbour(v)

        def get_neighbours(self):
            neighbours_copy = []
            for n in self._neighbours:
                neighbours_copy.append((n[0].get_value(), n[1]))
            return neighbours_copy
        
        def remove_neighbour(self, x): # O( k(x) ), k: grado del nodo
            k = len(self._neighbours) - 1
            removed = False
            while k >= 0:
                if self._neighbours[k][0].get_value() == x:
                    removed = True
                    self._neighbours.pop(k)
                    break
                k -= 1
            return removed

        def set_weight(self, y, w):
            i = 0
            mod = False
            while i < len(self._neighbours):
                k = self._neighbours[i]
                if k[0].get_value() == y:
                    self._neighbours[i] = (k[0], w)
                    mod = True
                    break
                i += 1
            return mod

        def set_value(self, new_value):
            self._value = new_value

    def __init__(self):
        self._nodes = {}
        self._number_of_edges = 0 # risparmio il conteggio degli archi

    def add_node(self, value):
        """
        :param value: key
        :return:
        """
        if value in self._nodes:
            return False
        n = self.Node(value)
        self._nodes[value] = n
        return True

    def get_nodes(self): # O(n)
        return list(self._nodes.keys())

    def number_of_nodes(self):
        return len(self._nodes)

    def number_of_edges(self):
        return self._number_of_edges

    def degree(self, x):
        if x in self._nodes:
            self._nodes[x].degree()
        return None

    def has_edge(self, x, y):
        if x in self._nodes and y in self._nodes:
            xn, yn = self._nodes[x], self._nodes[y]
        else:
            return False
        if self.degree(x) >= self.degree(y):
            neighbours = yn.get_neighbours()
            target = x
        else:
            neighbours = xn.get_neighbours()
            target = y
        for neighbour in neighbours:
            if neighbour[0] == target:
                return True
        return False

    def add_edge(self, x, y):
        if self.has_edge(x, y):
            return False
        
        node1, node2 = None, None
        if x in self._nodes and y in self._nodes:
            node1 = self._nodes[x]
            node2 = self._nodes[y]
        if (node1 is not None) and (node2 is not None):
            self._number_of_edges += 1
            node1.add_neighbours((node2, w))
            node2.add_neighbours((node1, w))
            return True
        return False

    def remove_edge(self, x, y):
        removed = False
        if x in self._nodes:
            removed = self._nodes[x].remove_neighbor(y)

        if not removed:
            return removed

        if y in self._nodes:
            self._nodes[y].remove_neighbor(x)

        self._number_of_edges -= 1

        return removed

    def remove_node(self, x):
        if x in self._nodes:
            xn = self._nodes.pop(x)
            for node in self._nodes:
                removed = self._nodes[node].remove_neighbor(xn.get_value())
                if removed:
                    self._number_of_edges -= 1
            return xn.get_value()
        return None

    def set_value(self, x, new_value):
        if x in self._nodes and new_value not in self._nodes:
            xn = self._nodes[x]
            xn.set_value(new_value)
            node = self._nodes.pop(x)
            self._nodes[new_value] = node
            return x
        return None

    def to_adjecency_matrix(self): # mapping lessicografico dei nodi sugli indici della matrice
        n = self.number_of_nodes()
        adj = [ [math.inf for _ in range(n)] for _ in range(n) ]
        mapping = {key: idx for idx, keey in enumerate(sorted(self._nodes.keys()))}
        for node in self.get_nodes():
            neighbours = self.neighbours(node)
            for k, w in neighbours:
                adj[mapping[node]][mapping[k]] = w
        return adj, mapping

    def to_adjecency_list(self):
        adj_i = []
        nodes = self.get_nodes()
        mapping = {key: idx for idx, keey in enumerate(sorted(self._nodes.keys()))}
        for node in nodes:
            adj_i.append([])
            neighbours = self.neighbours(node)
            for k, w in neighbours:
                adj_i[mapping[node]].append((mapping[k], w))
        return adj_i, mapping

if __name__ == "__main__":
        
    G = Graph()
