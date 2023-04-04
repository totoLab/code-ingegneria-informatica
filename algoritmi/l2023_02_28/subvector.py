# esercizio per casa - ppt 1

def subvector_somma_max(V):
  somma = sum(V)
  low, up = 0, len(V) - 1
  while low < up:
    if somma - V[low] > somma:
      low += 1
      somma = somma - V[low]
    if somma - V[up] > somma:
      up -= 1
      somma = somma - V[up]
  return V[low:up+1]

V = [1,3,4,-8,2,3,-1,3,4,-3,10,-3,2]
print(miao(V))

