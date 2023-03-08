# successione di fibonacci 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, ...

import time

def fib_r(n):
	if n == 0 or n == 1:
		return n
	return fib_r(n - 1) + fib_r(n - 2)

def fib_list(n):
	F = [0, 1]
	for i in range(2, n + 1):
		F.append(F[i - 1] + F[i - 2])
	return F[n]

def fib_i(n):
	if n == 0 or n == 1:
		return n

	precedente = 0
	corrente = 1
	s = 1
	for i in range(2, n):
		precedente = corrente
		corrente = s
		s = precedente + corrente
	return s

# riscrivere la versione ricorsiva con una sola chiamata ricorsiva all'interno

def fib_r_single(n):
	if n == 0 or n == 1:
		return n
	# TODO


n = 10
t0 = time.time()
v1 = fib_i(n)
t1 = time.time()
v2 = fib_list(n)
t2 = time.time()
v3 = fib_r(n)
t3 = time.time()
print(f"Fibonacci Iterativo: {v1}, {t1 - t0} ms")
print(f"Fibonacci Iterativo Lista: {v2}, {t2 - t1} ms")
print(f"Fibonacci Ricorsivo: {v3}, {t3 - t2} ms")