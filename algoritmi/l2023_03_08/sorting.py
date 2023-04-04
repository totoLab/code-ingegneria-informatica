def insertion_sort(V):
    for i in range(1, len(V)):
        vMin = V[i]
        j = i
        while j > 0 and vMin < V[j - 1]:
            V[j] = V[j - 1]
            j -= 1
        V[j] = vMin                                    

def selection_sort(V):
    for i in range(len(V) - 1):
        minp = i
        for j in range(i + 1, len(V)):
            if V[j] < V[minp]:
                minp = j
        V[minp], V[i] = V[i], V[minp]

def bubble_sort(V):
    n = len(V) - 1
    for i in range(n):
        for j in range(n - i):
            if V[j + 1] < V[j]:
                V[j + 1], V[j] = V[j], V[j + 1]

def bubble_sort_optimized(V):
    n = len(V) - 1
    for i in range(n):
        swap = False
        for j in range(n - i):
            if V[j + 1] < V[j]:
                V[j + 1], V[j] = V[j], V[j + 1]
                swap = True
        if not swap:
            break

def bubble_sort_optimized_w(V):
    n = len(V) - 1
    i = 0
    swap = True
    while i < n and swap:
        swap = False
        for j in range(n - i):
            if V[j + 1] < V[j]:
                V[j + 1], V[j] = V[j], V[j + 1]
                swap = True
        i += 1

V = [5, 7, 2, 9, 6, 12, 4, 11, 1, 3]
# insertion_sort(V)
# selection_sort(V)
# bubble_sort(V)
# bubble_sort_optimized(V)
# bubble_sort_optimized_w(V)
print(V)