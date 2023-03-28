def createTree():
    return []

def key(T):
    return T[0]

def left(T):
    return T[1]

def left(T):
    return T[2]

def addLeafNode(T, x):
    T.append(x)
    T.append([])
    T.append([])

def deleteLeafNode(T):
    T.pop()
    T.pop()
    T.pop()

def swapKeys(T1, T2):
    k = key(T1)
    setKey(T1, key(T2))
    setKey(T2, k)

def int2string(n): # notazione binaria senza l'ultima cifra theta(log_2(n))
    s = []
    while n > 0:
        s.append(n % 2)
        n = n // 2
    s.pop()
    return s

def last(s):
    return s[len(s) - 1]

# --------------- heap --------------- #

def createHeap():
    return [createTree(), 0]

def tree(H):
    return H[0]

def size(H):
    return H[1]

def emptyHeap(H):
    return size(H) == 0 # oppure empty(tree(H))

def setSize(H, n):
    H[1] = n

def min(H):  # theta(1)
    if emptyHeap(H):
        print("Errore: heap vuoto")
        return None
    return key(tree(H))

def insertHeap(H, x): # theta(log_2(n))
    setSize(H, size(H) + 1)
    s = int2string(size(H))
    insertTree(tree(H), s, x)

def insertTree(T, s, x): # theta(log_2(n))
    if s == []:
        addLeafNode(T, x)
    elif last(s) == 0:
        s.pop()
        insertTree(left(T), s, x)
        if key(T) > key(left(T)):
            swapKeys(T, left(T))
    else:
        s.pop()
        insertTree(right(T), s, x)
        if key(T) > key(right(T)):
            swapKeys(T, right(T))

def deleteMin():
    if emptyHeap(H):
        print("Errore: heap vuoto")
        return None
    A = tree(H)
    n = size(H)
    min = min(H)
    if n == 1:
        deleteLeafNode(A)
        setSize(H, 0)
    else:
        s = int2string(n)
        while len(s) > 0:
            if last(s) == 0:
                A = left(A)
            else:
                A = right(A)
            s.pop()
        deleteLeafNode(A)
        setKey(tree(H), key(A))
        A = tree(H)
        while ( not emptyTree(left(A)) and key(A) > key(left(A)) ) or \
            ( not emptyTree(right(A)) and key(A) > key(right(A)) ):

            if emptyTree(left(A)) and key(A) < key(left(A)):
                swapKeys(A, left(A))
                A = left(A)
            else:
                swapKeys(A, right(A))
                A = right(A)
    return min