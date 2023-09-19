from graph import *


"""
Es. 1
Sia G un grafo sociale in cui i nodi rappresentano utenti, 
gli archi indicano relazioni di amicizia, e il peso associato agli archi la "forza" dell'amicizia. Definire un metodo **strength(G, k, h)**, che restituisce l’identificativo di tutti gli utenti con almeno k amici connessi con forza maggiore o uguale ad h.
"""


def strength(G, k, h):

    res = []
    nodes = G.get_nodes()
    for n in nodes:
        if G.degree(n) >= k:
            neigh = G.neighbors(n)  # grado(n)
            count = 0
            for [_, w] in neigh:
                if w >= h:
                    count += 1
            if count >= k:
                res.append(n)

    return res


"""
Es. 2

Sia G un grafo sociale in cui i nodi rappresentano utenti, e gli archi indicano 
relazioni di amicizia. Definire un metodo **popular(G)** che restituisca l’utente che ha
 il maggior numero di “amici” e “amici di amici”, ovvero nodi a distanza 1 o 2 da esso. Nota bene: nel calcolo un utente non deve essere contato più di una volta e l’insieme degli “amici degli amici” di un dato utente "A" non deve contenere "A" stesso.
"""

def popolaritaNodo(G, x):
    count = 0
    visited = {} # nodi già visitati
    visited[x] = True
    adiacenti = G.neighbors(x)
    for [j, _] in adiacenti:
        if j not in visited or not visited[j]:
            visited[j] = True
            count += 1
        amici2 = G.neighbors(j)
        for [h, _] in amici2:
            if h not in visited or not visited[h]:
                visited[h] = True
                count += 1
    return count


def poplarita(G):
    nodes = G.get_nodes()
    v_max = -1
    nodo_max = -1
    for n in nodes:
        pi = popolaritaNodo(G, n)  # O(n*m)
        print(f'Popolarità nodo {n} : {pi}')
        if pi > v_max:
            v_max = pi
            nodo_max = n
    return nodo_max



if __name__ == '__main__':

    G = Graph()

    nodes = ['A', 'B', 'C', 'D', 'E', 'F', 'G']
    for n in nodes:
        G.add_node(n)
    edges = [['A', 'B', 2], ['A', 'C', 13], ['B', 'D', 2], ['C', 'D', 1], ['D', 'E', 4], ['D', 'F', 2], ['A', 'G', 9]]

    for e in edges:
        G.add_edge(e[0], e[1], e[2])

    s = strength(G, 3, 2)
    print('Strength : ', s)
    print()
    p = poplarita(G)
    print()
    print(f'Nodo più popolare : {p}')