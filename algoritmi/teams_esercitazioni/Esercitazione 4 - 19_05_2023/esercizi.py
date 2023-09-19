from bst import *


"""
Scrivere una funzione ricorsiva fogliaVicina(A), che calcola la distanza dalla foglia più vicina alla radice di A


"""


def fogliaVicina(A):

    if empty(A):
        return 0

    dl = fogliaVicina(left(A))
    dr = fogliaVicina(right(A))
    return 1 + min(dl, dr)


if __name__ == '__main__':
    C = [8, 3, 1, 6, 4, 7, 10, 14, 13]

    A = createTree()
    for n in C:
        insert(A, n)
    printTree(A)
    print(fogliaVicina(A))


