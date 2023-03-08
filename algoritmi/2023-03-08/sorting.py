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
