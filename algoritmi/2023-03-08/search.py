def linear_search(V, x):
  for i in range(len(V)):
    if x == V[i]:
      return i
  return -1
  
def binary_search(V, x):
  first = 0
  last = len(V) - 1
  while first <= last:
    middle = (last + first) // 2
    if x == V[middle]:
      return middle
    if x < V[middle]:
      last = middle - 1
    else:
      first = middle + 1
  return -1    