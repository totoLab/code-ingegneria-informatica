def concatenate(A, B):
    C = []
    for i in range(len(A)):
        C.append(A[i])
    for i in range(len(B)):
        C.append(B[i])
    return C

def merge(A, B):
    i, j = 0, 0
    n, m = len(A), len(B)
    C = []
    while i < n and j < m:
        if A[i] <= B[j]:
            C.append(A[i])
            i += 1
        else:
            C.append(B[j])
            j += 1

    while i < n:
        C.append(A[i])
        i += 1

    while j < m:
        C.append(B[j])
        j += 1

    return C

# TODO import insertion_sort

def conc_sort1(A, B):
    C = merge(A, B)
    insertion_sort(C)
    return C

def conc_sort2(A, B):
    insertion_sort(A)
    insertion_sort(B)
    C = merge(A, B)
    return C