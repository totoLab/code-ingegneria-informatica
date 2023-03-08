# Trovare il sotto-vettore A[i ... j] di somma massimale, ovvero il sotto-vettore la cui somma degli elementi
# \Sum_{k=i}^jA[k] è più grande o uguale alla somma degli elementi di qualunque altro sotto-vettore.

def sotto_vettore_somma_max(V):
  somma = sum(V)
  low, up = 0, len(V) - 1
  run = True
  while low < up and run:
    run = False

    nuovaSomma = somma - V[low]
    if nuovaSomma >= somma:
      low += 1
      somma = nuovaSomma
      run = True

    nuovaSomma = somma - V[up]
    if nuovaSomma >= somma:
      up -= 1
      somma = nuovaSomma
      run = True

  return V[low:up+1]

# Il problema del sotto-vettore di somma massimale può essere risolto utilizzando l'algoritmo di Kadane.
# L'algoritmo di Kadane è un algoritmo efficiente per la risoluzione di questo problema con un tempo
# di esecuzione O(n). L'idea di base dell'algoritmo è di mantenere due variabili:
# la somma corrente del sotto-vettore (curr_sum) e la somma massima del sotto-vettore (max_sum).
# Inizialmente, entrambe le variabili sono impostate a zero. Successivamente, si scansiona l'intero vettore.
# Per ogni elemento, si aggiunge il valore dell'elemento alla somma corrente del sotto-vettore (curr_sum)
# e si controlla se la somma corrente è maggiore della somma massima del sotto-vettore (max_sum).
# Se è così, si aggiorna il valore della somma massima del sotto-vettore (max_sum).
# Se la somma corrente diventa negativa, viene impostata a zero, poiché non può contribuire ad aumentare
# la somma del sotto-vettore. L'algoritmo restituisce il valore della somma massima del sotto-vettore (max_sum)
#  e può anche essere modificato per restituire gli indici del sotto-vettore. Di seguito è riportata l'implementazione
# dell'algoritmo di Kadane in Python per risolvere il problema del sotto-vettore di somma massimale:

def maxSubArraySum(A):
    curr_sum = 0
    max_sum = -float('inf')
    start_index = end_index = 0
    
    for i in range(len(A)):
        curr_sum += A[i]
        if curr_sum > max_sum:
            max_sum = curr_sum
            end_index = i
        if curr_sum < 0:
            curr_sum = 0
            start_index = i+1
    
    return A[start_index:end_index+1]

# esempio
V = [1,3,4,-8,2,3,-1,3,4,-3,10,-3,2]
