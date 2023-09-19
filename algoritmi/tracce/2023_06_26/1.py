def twoProd(A, z):
    return entryPoint(A, z, A, 0)

def entryPoint(A, z, src, d):
    if isNull(A) or isLeaf(A):
        return False
    if d % 2 == 0 and not verifica(src, A, z, 0):
        return False
    return entryPoint(left(A), z, src, d + 1) and entryPoint(right(A), z, src, d + 1)

def verifica(T, node, z, d):
    if isNull(T) or isLeaf(T):
        return False
    if d % 2 == 0:
        if info(T) * info(node) != z:
            return False
    return verifica(left(T), node, z, d + 1) and verifica(right(T), node, z, d + 1) 