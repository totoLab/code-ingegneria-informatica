# 1. scrivere un metodo pocoProfondo(A) che restituisce True se e solo se esiste un nodo foglia in A il cui valore è non maggiore di tutti i suoi antenati

def pocoProfondo(A):
    return pocoProfondoR(A, t.value(A))

def pocoProfondoR(A, minimo):
    if empty(A):
        return False
        
    if t.isLeaf(A):
        return A <= minimo
    
    minimo = min(minimo, t.value(A))
    return pocoProfondoR(left(A), minimo) or pocoProfondoR(right(A), minimo)
 
# 2. preso un divide et impera che a ogni passo suddivide l'istanza corrente in 3 sottoistanze dello stesso problema
# ciascuno di dimensione n/3 (dove n è la dimensione del problema) si ricavi la complessità supponendo che
# al netto delle chiamate ricorsive la singola chiamata abbia complessità bn^3, con b costante