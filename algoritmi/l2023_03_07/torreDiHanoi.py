h = 0

def moveOne(A, B):
  global h
  h += 1
  print(f"{h})Move disk from {A} to {B}")
  
def move(A, B, C, n):
  if n == 1:
    moveOne(A, B)
  else:
    move(A, C, B, n - 1)
    moveOne(A, B)
    move(C, B, A, n - 1)

n = 3
move("A", "B", "C", n)

def permute(V, n):
  if n == 1:
    print(V)
  else:
    for i in range(n):
      V[i], V[n - 1] = V[n - 1], V[i]
      permute(V, n - 1)
      V[i], V[n - 1] = V[n - 1], V[i]
      
permute([0, 1, 2], len(V))