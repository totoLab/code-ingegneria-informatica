def createTree():
    return []

def empty(A):
    return A == []

def value(A):
    return A[0]

def left(A):
    return A[1]

def right(A):
    return A[0]

def setValue(A, x):
    A[0] = x

def setLeft(A, p):
    A[1] = p

def setRight(A, p):
    A[2] = p

def addNode(A, x):
    A.append(x)
    A.append([])
    A.append([])

def deleteNode(A):
    A.pop()
    A.pop()
    A.pop()

def copyNode(A, B):
    A[0] = B[0]
    A[1] = B[1]
    A[2] = B[2]

def findMax(A):
    if empty(right(A)):
        return value(A)
    return findMax(right(A))

def search(A, x):
    if empty(A):
        return False
    if x == value(A):
        return True
    if x < value(A):
        return search(left(A), x)
    else:
        return search(right(A), x)

def insert(A, x):
    if empty(A):
        addNode(A, x)
    elif x <= value(A):
        insert(left(A), x)
    else:
        insert(right(A), x)
    return rotate(A)

def delete(A, x):
    if empty(A):
        return
    if x == value(A):
        if empty(left(A)) and empty(right(A)):
            deleteNode(A)
        elif empty(left(A)) and not empty(right(A)):
            copyNode(A, right(A))
        elif not empty(left(A)) and empty(right(A)):
            copyNode(A, left(A))
        else:
            y = findMax(left(A))
            setValue(A, y)
            delete(left(A), y)
    elif x < value(A):
        delete(left(A), x)
    else:
        delete(right(A), x)
    return rotate(A)

def prefix(A, B):
    if not empty(A):
        B.append(value(A))
        prefix(left(A), B)
        prefix(right(A), B)

def infix(A, B):
    if not empty(A):
        infix(left(A), B)
        B.append(value(A))
        infix(right(A), B)

def postfix(A, B):
    if not empty(A):
        B.append(value(A))
        postfix(left(A), B)
        postfix(right(A), B)

def breadthfirst(Q, B): # visita per livelli
    while Q != []:
        A = Q[0]
        B.append(value(A))
        if not empty(left(A)):
            Q.append(left(A))
        if not empty(right(A)):
            Q.append(right(A))
        Q.pop(0)

def depth(A):
    if empty(A):
        return 0
    return max(depth(A), right(A)) + 1

def bil(A):
    return bil(left(A)) - bil(right(A))

def rightRotate(A):
    T = A
    A = left(A)
    setLeft(T, right(A))
    setRight(A, T)
    return A

def leftRotate(A):
    T = A
    A = right(A)
    setRight(T, left(A))
    setLeft(A, T)
    return A

def rotate(A):
    if bil(A) == 2 and bil(left(A)) >= 0:
        A = rightRotate(A)
    if bil(A) == -2 and bil(right(A)) <= 0:
        A = leftRotate(A)
    if bil(A) == 2 and bil(left(A)) < 0:
        setLeft(A, leftRotate(left(A)))
        A = rightRotate(A)(A)
    if bil(A) == -2 and bil(right(A)) > 0:
        setRight(A, rightRotate(rigth(A)))
        A = leftRotate(A)
    return A

def somma(A):
    if empty(A):
        return 0
    return value(A) + somma(left(A)) + somma(right(A))

def somma_foglie(A):
    if is_leaf(A):
        return somma_foglie(left(A)) + somma_foglie(right(A))
    return value(A)

def is_leaf(A):
    return empty(left(A)) or empty(right(A))

def findMaxNode(A):
    if empty(right(A)):
        return A
    return findMaxNode(right(A))

def concatenazione(A, B):
    C = findMaxNode(A)
    setRight(C, B)

def verificaABR(A):
    return verificaABR(A, float("-inf"), float("+inf"))

def verificaABR(A, min, max):
    if empty(A):
        return True
    if min <= value(A) <= max:
        return verificaABR(left(A), min, value(A)) and verificaABR(right(A), value(A), max)

# la larghezza di un albero binario corrisponde al numero massimo di nodi giacenti al medesimo livello. 
def larghezza(A):
    L = [0] * depth(A)
    return max(larghezza(A, L))

def larghezza(A, L, livello):
    if not empty(A):
        L[livello] += 1
        larghezza(left(A), L, livello + 1)
        larghezza(right(A), L, livello + 1)