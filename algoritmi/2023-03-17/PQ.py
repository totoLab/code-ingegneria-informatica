def create():
    return []

def min(Q):
    if len(Q) == 0:
        print("Priority queue empty")
        return None
    return Q[0]

def insert(Q, x):
    Q.append(x)
    i = len(Q) - 1
    while i > 0 and Q[i] < Q[(i-1) // 2]:
        Q[i], Q[(i-1) // 2] = Q[(i-1) // 2],  Q[i]
        i = (i - 1) // 2

def delete_min(Q):
    if len(Q) == 0:
        print("Priority queue empty")
        return None
    min = Q[0]
    Q[0] = Q.pop()

    i = 0
    while ( (2 * i + 1) <= len(Q) - 1 and Q[i] > Q[2 * i + 1] ) or \
        ( (2 * i + 2) <= len(Q) - 1 and Q[i] > Q[2 * i + 2]) :
        
        if (2 * i + 2) <= len(Q) - 1 and Q[2*i+2] < Q[2*i + 1]: # (esistono entrambi i figli) and (figlio destro > figlio sinistro)
            Q[2*i+2], Q[i] = Q[i], Q[2*i+2]
            i = 2 * i + 2
        else:
            Q[2*i+1], Q[i] = Q[i], Q[2*i+1]
            i = 2 * i + 1
