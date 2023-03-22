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

# TODO valutare la complessità