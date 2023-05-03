import PriorityQueue as h

def Huffman(L): # O(2n log2n) + \theta(n)) = O(n log2 n)
    Q = h.createHeap()
    n = len(L)
    for i in range(n): # O(n log2 n)
        h.insertHeap(Q, L[i])
    for i in range(n - 1): # O(n log2 n)
        B = h.deleteMin(Q) # primo estratto figlio sinistro
        A = h.delenteMin(Q) # secondo estratto figlio destro
        C = [ [A[0] + "+" + B[0], A, B], A[1] + B[1] ]
        h.insertHeap(Q, C)
    D = h.deleteMin(Q)
    print(D)
    S = [] # [ (char: binary), ...]
    codeGenerator(D, S, []) # \theta(n + n - 1) = \theta(2n) = \theta(n)
    return S

def codeGenerator(T, S, C): # T: tree, _, C: bits
    if leaf(T):         # assign bit list
        C1 = copyList(C)
        S.append([T[0], C1])
    else:                   # recursively visit both child nodes
        C.append(0)
        codeGenerator(T[0][1], S, C)
        C.pop()
        C.append(1)
        codeGenerator(T[0][2], S, C)
        C.pop()

def leaf(T):
    return T[0] in [ char for [char, freq] in L ] # si trova nella lista degli id originali

def copyList(L):
    return L[:]

L = [ ["a", 45], ["b", 13], ["c", 12], ["d", 16], ["e", 9], ["f", 5] ]
Huffman(L)