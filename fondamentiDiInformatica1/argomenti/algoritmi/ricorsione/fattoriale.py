def fattoriale(n):
    if n == 1:
        return 1

    return n * fattoriale(n - 1)

def fib(n):
    if n == 1 or n == 2:
        return 1

    return fib(n - 1) + fib(n - 2)

def stampa_primi_n_fibonacci(n):
    print(1)
    print(1)
    ultimo = 1
    penultimo = 1
    for i in range(n - 2):
        nuovo = ultimo + penultimo
        print(nuovo)
        penultimo = ultimo
        ultimo = nuovo

stampa_primi_n_fibonacci(10)