import heap as hp

def heap_sort(C):
    H = hp.createHeap()
    for i in range(len(C)):
        hp.insertHeap(H, C[i])
    for i in range(len(C)):
        C[i] = hp.deleteMin(H)

