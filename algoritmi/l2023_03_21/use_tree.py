import tree as t

C = [10, 5, 20, 3, 8, 1, 4, 7, 9, 15, 25, 12, 18, 22, 27]

A = t.createTree()
for i in range(len(C)):
    t.insert(A, C[i])

print(C)
print(A)