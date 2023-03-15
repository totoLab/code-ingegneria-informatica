import numpy as np
from scipy import signal

import matplotlib.pyplot as plt

# ipotesi: quando il giocatore va in prigione paga immediatamente la multa


# matrici A, B, C, D
A = [
     [0, 0, 0, 0, 0, 0, 0.5],
     [0.5, 0, 0, 0, 0, 0, 0.5],
     [0.5, 0.5, 0, 0, 0.5, 0.5, 0],
     [0, 0.5, 0.5, 0, 0, 0, 0],
     [0, 0, 0.5, 0.5, 0, 0, 0],
     [0, 0, 0, 0.5, 0.5, 0, 0],
     [0, 0, 0, 0, 0, 0.5, 0],
]

n = np.shape(A)[0]

B = np.zeros((n, 1))
C = np.identity(n)
D = np.zeros((n, 1))

# creazione sistema dinamico
monopoli = signal.dlti(A, B, C, D)

# definisco il numero di lanci
t = np.arange(15)

# stato iniziale
x0 = [1.0, 0, 0, 0, 0, 0, 0] # parto dal via x1(k) = 1 (evento certo)

# ingressi
u = np.zeros((len(t), 1))

# simulazione
_, _, x = signal.dlsim(monopoli, u, t, x0)

# vettore dei pedaggi
P = [0, 15.0, 0, 35.0, 40.0, 42.0, 90.0]
risultato = P*x[-1,:]