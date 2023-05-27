# 1. Scrivere una funzione mediaNonFoglia che riceve in input un albero binario T (la cui parte informativa è costituita da un numero intero) e restituisce restituisce un numero reale dato dalla media dei valori contenuti nei nodi non foglia del solo sottoalbero destro dell’albero T in input. Scrivere una funzione mediaNonFoglia che riceve in input un albero binario T (la cui parte informativa è costituita da un numero intero) e restituisce restituisce un numero reale dato dalla media dei valori contenuti nei nodi non foglia del solo sottoalbero destro dell’albero T in input. 

def mediaNonFoglia(T):
    lista_valori = []
    rec(right(T), lista_valori)
    n = len(lista_valori)
    if (n > 0):
        return sum(lista_valori)/len(lista_valori)
    return 0

def media(T, L):
    if not (t.empty(T) or t.isLeaf(T)):
        L.append(t.value(T))
        media(t.right(A), L)
        media(t.left(A), L)

def mnF(A):
    return mediamnF(right(A), right(A))

def mediamnF(A, root):
    if t.empty(A) or t.isLeaf(A):
        return 0, 0
    sumLeft, lenghtLeft = mediamnF(left(A), root)
    sumRight, lenghtRight = mediamnF(right(A), root)
    if A == root:
        return (sumLeft + sumRight + 1) / (lenghtLeft + lenghtRight + 1)
    return sumLeft + sumRight + t.value(A), lenghtLeft + lenghtRight+ 1

'''
2. Analizzare, attraverso l'uso delle relazioni di ricorrenza, il caso migliore per la complessità temporale dell'algoritmo QuickSort. 
       subarray1  subarray2   divisione
T(n) = T(a)     + T(b)      + \theta(n)

caso migliore con partizioni a e b sempre bilanciati (lunghezza n/2) per ogni chiamata

-> T(n) = T(n/2) + T(n/2) + \theta(n) = 
        = 2T(n/2) + \theta(n) = ?
        
Master theorem:
T(n) = aT(n/b) + f(n), a > 1, b > 1, f non decr.

1. T(n^log_b(a))            se f(n) = O(n^(log_b(a) - \eps)), \eps > 0
2. T(n^log_b(a) * log n)    se f(n) = \theta(n^(log_b(a)) -> T(log n * f(n) )
3. T(n) = \theta(f(n))      se f(n) = \Omega(n^(log_b(a) + \eps)), \eps > 0 e a * f(n/b) <= c * f(n), c > 1

siamo nel caso 2 dove f(n) = \theta(...)
'''