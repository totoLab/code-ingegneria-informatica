
class Graph:
    """
        Graph class which represents the undirected graph data structure.
        It contains a nodes attribute (dict) with all the nodes of the graph
    """

    class Node:
        """
            The Node class represents each vertex of the graph
            The attribute value represents the stored data
            The list of neighbors attribute represents the vertices with which exists a connection
        """

        def __init__(self, value):
            self._value = value
            self._neighbors = []

        def degree(self):
            return len(self._neighbors)

        def add_neighbor(self, neighbor):
            self._neighbors.append(neighbor)

        def add_neighbors(self, neighbors):
            for v in neighbors:
                self.add_neighbor(v)

        def get_neighbors(self):
            neighbor_copy = []
            for n in self._neighbors:
                neighbor_copy.append((n[0].get_value(), n[1]))
            return neighbor_copy

        def remove_neighbor(self, x):
            """

            :param x: key of the node to remove
            :return: True if the node is removed and False otherwise
            """
            k = len(self._neighbors) - 1
            removed = False
            while k >= 0:
                if self._neighbors[k][0].get_value() == x:
                    removed = True
                    self._neighbors.pop(k)
                    break
                k -= 1
            return removed

        def get_value(self):
            return self._value

        def set_value(self, value):
            self._value = value

        def set_weight(self, y, w):
            """
            Change the the neighbor y with w
            :param y:
            :param w:
            :return:
            """
            i = 0
            while i < len(self._neighbors):
                k = self._neighbors[i]
                if k[0].get_value() == y:
                    self._neighbors[i] = (k[0], w)
                i += 1

        def __repr__(self):
            returned_string = f"{self._value} -> "
            if self.degree() > 0:
                for neighboor in self._neighbors:
                    returned_string += f"{neighboor[0].get_value()} -> "
            returned_string += "None"
            return returned_string

    def __init__(self):
        self._nodes = {}
        self._number_of_edges = 0

    def add_node(self, value):
        """
        Add a new vertex in the graph
        :param value: type of the node
        :return: True if the node was added, and False otherwise
        """
        if value in self._nodes:
            return False
        self._nodes[value] = self.Node(value)
        return True

    def get_nodes(self):
        """
        Get a copy of the nod
        :return:
        """
        return list(self._nodes.keys())

    def number_of_nodes(self):
        """
        Compute the number of nodes in the graph
        :return: int
        """
        return len(self._nodes)

    def number_of_edges(self):
        """
        Return the number of edges of the graph
        :return: int
        """
        return self._number_of_edges

    def degree(self, x):
        """
        Compute the degree of node with key x
        :param x: key of node
        :return: the degree if the node is in the graph and None otherwise
        """
        if x in self._nodes:
            return self._nodes[x].degree()
        else:
            raise ValueError(f'Node with key {x} does not exists!')

    def has_node(self, x):
        """
        :param x:
        :return: True if node with key x exists
        """
        if x in self._nodes:
            return True
        return False

    def has_edge(self, x, y):
        """
        Return true if x and y are connected
        :param x:
        :param y:
        :return: True if the edge between x and y exists, and False otherwise
        """

        if x in self._nodes and y in self._nodes:
            xn, yn = self._nodes[x], self._nodes[y]
        else:
            return False
        if self.degree(x) >= self.degree(y):
            neigh = xn.get_neighbors()
            target = yn.get_value()
        else:
            neigh = yn.get_neighbors()
            target = xn.get_value()
        for neighbor in neigh:
            if neighbor[0] == target:
                return True
        return False

    def get_weight(self, x, y):
        """

        :param x:
        :param y:
        :return: weightb between the edge x and y
        """

        if not self.has_edge(x, y):
            return None

        xn, yn = self._nodes[x], self._nodes[y]

        if self.degree(x) >= self.degree(y):
            neigh = yn.get_neighbors()
            target = xn.get_value()
        else:
            neigh = xn.get_neighbors()
            target = yn.get_value()
        for neighbor in neigh:
            if neighbor[0] == target:
                return neighbor[1]

    def set_weight(self, x, y, w):
        if not self.has_edge(x, y):
            return None

        xn, yn = self._nodes[x], self._nodes[y]
        xn.set_weight(y, w)
        yn.set_weight(x, w)

    def neighbors(self, x):
        """
        Return the neighbors of node with key node
        :param x:
        :return: List of neighbors of node with key x
        """
        if x in self._nodes:
            return self._nodes[x].get_neighbors()
        else:
            print(f'Node with key {x} does not exists!')
            return None

    def add_edge(self, x, y, w=1):
        """
        Add an edge between nodes with key 'x' and 'y', with weight 'w'
        :param x:
        :param y:
        :param w:
        :return: True if the add was added, False otherwise
        """

        if self.has_edge(x, y):
            return False

        node1, node2 = None, None
        if x in self._nodes and y in self._nodes:
            node1, node2 = self._nodes[x], self._nodes[y]

        if (node1 is not None) and (node2 is not None):
            self._number_of_edges += 1
            node1.add_neighbor((node2, w))
            node2.add_neighbor((node1, w))
            return True
        else:
            #print("Warning: One or more nodes were not found. No edge was added.")
            return False

    def remove_edge(self, x, y):
        """
        Remove the edge between node x and y
        :param x:
        :param y:
        :return: True if the Node is removed, False otherwise
        """

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
        """
        Remove the node with value x
        :return:  The node removed
        """
        if x in self._nodes:
            xn = self._nodes.pop(x)
            for node in self._nodes:
                removed = self._nodes[node].remove_neighbor(xn.get_value())
                if removed:
                    self._number_of_edges -= 1
            return xn.get_value()
        return None

    def set_value(self, x, new_val):  # O(m)

        if x in self._nodes and new_val not in self._nodes:
            xn = self._nodes[x]
            xn.set_value(new_val)
            node = self._nodes.pop(x)
            self._nodes[new_val] = node
        return None

    def to_list_of_edges(self, discard_w=False):
        """
        Return the graph as a list of edges
        :return:
        """

        lio = []
        for n in self._nodes:
            neigh = self.neighbors(self._nodes[n].get_value())
            for k in neigh:
                if discard_w:
                    lio.append((n, k[0]))
                else:
                    lio.append((n, k[0], k[1]))
        return lio

    def to_adjacency_matrix(self):
        n = self.number_of_nodes()
        adj = [[0 for _ in range(n)] for _ in range(n)]  # O(n^2)
        nodes = self.get_nodes()
        mapping = {key: idx for idx, key in enumerate(sorted(nodes))}
        for node in nodes:
            neigh = self.neighbors(node)
            for k, w in neigh:
                adj[mapping[node]][mapping[k]] = w
        return adj, mapping

    def to_adjacency_list(self):
        adj_l = []
        nodes = self.get_nodes()
        mapping = {key: idx for idx, key in enumerate(sorted(nodes))}
        for node in nodes:
            adj_l.append([])
            neigh = self.neighbors(node)
            for k, w in neigh:
                adj_l[mapping[node]].append([mapping[k], w])
        return adj_l, mapping

    def clear(self):
        self._nodes.clear()
        self._number_of_edges = 0

if __name__ == '__main__':
    G = Graph()

    nodes = ['Giovanna', 'Federico', 'C', 'D', 'E', 'F', 'G']
    edges = [['Giovanna', 'Federico', 2], ['Giovanna', 'C', 13], ['Federico', 'D', 2], ['C', 'D', 1], ['D', 'E', 4],
             ['D', 'F', 2], ['Giovanna', 'G', 9]]

    for e in edges:
        G.add_node(e[0])
        G.add_node(e[1])
        G.add_edge(e[0], e[1], e[2])

    # draw(G)
    neigh_Giovanna = G.neighbors('Giovanna')
    G.set_value('Giovanna', 'A')
    G.set_value('Federico', 'B')

    G.add_node('H')
    G.remove_edge('A', 'B')
    G.add_edge('A', 'H', 20)
    G.set_weight('A', 'C', 5)
    nnodes = G.get_nodes()
    edges = G.to_list_of_edges()
    neighA = G.neighbors('A')

