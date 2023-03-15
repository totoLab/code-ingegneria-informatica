def quicksort(A, first, last):
    if first < last:
        m = partition(A, first, last)
        quicksort(A, first, m)
        quicksort(A, m + 1, last)

def partition(A, first, last):
    i, j = first, last
    pivot = A[first]
    while i < j:
        while A[i] < pivot:
            i += 1
        while j > first and A[j] >= pivot:
            j -= 1
        if i < j:
            A[i], A[j] = A[j], A[i]
            i += 1
            j -= 1
    if i == j and A[j] >= pivot:
        j -= 1
    return j

V = [6, 8, 4, 5, 9, 3, 1, 8, 10, 4]
quicksort(V, 0, len(V) - 1)
print(V)