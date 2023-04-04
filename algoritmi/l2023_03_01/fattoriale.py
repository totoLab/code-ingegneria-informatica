import time

def factorial_i(n):
    ret = 1
    for i in range(2, n + 1):
        ret *= i
    return ret

def factorial_r(n):
    if n <= 1:
        return 1
    return n * factorial_r(n - 1)

n = 50
t0 = time.time()
v1 = factorial_i(n)
t1 = time.time()
v2 = factorial_r(n)
tf = time.time()
print(f"Fattoriale Iterativo: {v1}, {t1 - t0} ms")
print(f"Fattoriale Ricorsivo: {v2}, {tf - t1} ms")