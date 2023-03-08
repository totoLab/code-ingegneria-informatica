def sommaMatrici(M1, M2, M, i = 0):
  if i == len(M1):
    return
  M.append([])
  sommaVettori(M1[i], M2[i], M[i], 0)
  return sommaMatrici(M1, M2, M, i + 1)
  
def sommaVettori(V1, V2, V, i = 0):
  if i == len(V1):
    return
  V.append(V1[i] + V2[i])
  return sommaVettori(V1, V2, V, i + 1)

M1 = [
  [1, 2],
  [9, 10]
]

M2 = [
  [3, 4],
  [0, 1]
]

M = []

sommaMatrici(M1, M2, M)
print(M)