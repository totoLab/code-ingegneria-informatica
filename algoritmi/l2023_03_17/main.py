import PQ

def heap_sort(A):
    Q = PQ.create_from_list(A)
    for i in range(len(A)):
        A[i] = PQ.delete_min(Q)

V  = [5,7,2,8,4,9,6,1,3,7]
heap_sort(V)
print(V)