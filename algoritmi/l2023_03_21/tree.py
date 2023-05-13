def createTree():
    return []

def empty(A):
    return A == []

def value(A):
    return A[0]

def left(A):
    return A[1]

def right(A):
    return A[2]

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
        prefix(A, left(B))

def infix(A, B):
    if not empty(A):
        infix(left(A), B)
        B.append(value(A))
        infix(A, left(B))

def postfix(A, B):
    if not empty(A):
        B.append(value(A))
        postfix(left(A), B)
        postfix(A, left(B))

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