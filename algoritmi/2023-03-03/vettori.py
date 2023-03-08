def sommaV(V1, V2):
  V3 = []
  for i in range(len(V1)):
    V3.append(V1[i] + V2[i])
  return V3
  
# \theta(n) spaziale e temporale

def prodottoS(V1, V2):
  f = 0
  for i in range(len(V1)):
    f = f + V1[i] * V2[i]
  return f
  
# \theta(n) temporale, \theta(1) spaziale

def sommaM(A, B):
  C = []
  for i in range(len(A)):
    C.append([])
    for j in range(len(A[0]):
      C[i].append(A[i][j] + B[i][j])
  return C
  
# \theta(n^2) spaziale e temporale

def prodottoScalare(A, B):
  C = []
  for i in range(len(A)):
    C.append([])
    for j in range(len(B[0])):
      d = 0
      for k in range(len(A[0])):
        d += A[i][k] * B[k][j]
      C[i].append(d)
  return C

# \theta(n^2) spaziale, \theta(n^3) temporale

# esercizio: somma di vettori ricorsiva
def sommaVettori(V1, V2, index = 0):
  elemento = V1[index] + V2[index]
  if index == len(V1) - 1:
    return [elemento]
  return [elemento] + sommaVettori(V1, V2, index + 1)
  
print(sommaVettori([1, 2, 3], [1, 2, 3]))

