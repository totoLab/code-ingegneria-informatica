import numpy as np
from scipy import signal

import matplotlib.pyplot as plt

# modello gioco d'azzardo

# probabilità lancio moneta
p = 0.6

# matrici A, B, C, D
A = [
     [1, p, 0, 0, 0],
     [0, 0, p, 0, 0],
     [0, 1-p, 0, p, 0],
     [0, 0, 1-p, 0, 0],
     [0, 0, 0, 1-p, 1]
]

n = np.shape(A)[0]

B = np.zeros((n, 1))
C = np.identity(n)
D = np.zeros((n, 1))

# creazione sistema dinamico
gioco = signal.dlti(A, B, C, D)

# definisco il numero di lanci
t = np.arange(100)

# stato iniziale
x0 = [0, 0.4, 0.3, 0.3, 0]

# ingressi
u = np.zeros((len(t), 1))

# simulazione
_, _, x = signal.dlsim(gioco, u, t, x0)

# calcolo autovalori e autovettori dx di A

autovalori, autovettori = np.linalg.eig(A)

pi = autovettori[:,0]
