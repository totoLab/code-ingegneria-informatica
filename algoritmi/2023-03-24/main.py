import tree

# prova esame
def speculari(A, B):
    # iterate over A and search for 2 nodes same value in B
    if empty(A) or empty(B):
        return False
    if search2Values(B, tree.value(A)):
        return True
    return speculari(left(A), B) or speculari(right(A), B)

def search2Values(A, x, foundOne):
    if empty(A):
        return False
    if x == value(A):
        if foundOne:
            return True
        else:
            foundOne = True
    return search2Values(left(A), x, foundOne) or \
        search2Values(right(A), x, foundOne)


C = [10, 5, 20, 3, 8, 1, 4]
D = [7, 9, 15, 25, 5, 12, 5, 18, 22, 27]
A = tree.createTree()
B = tree.createTree()
for i in range(len(C)):
    tree.insert(A, C[i])

for i in range(len(D)):
    tree.insert(B, D[i])

print(speculari(A, B))