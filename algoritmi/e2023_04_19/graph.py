class Graph: # non orientato -> simmetrico (non c'è differenza tra archi entranti e uscenti) e pesato

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