

import numpy as np
from scipy import signal

import matplotlib.pyplot as plt

# Modello del sistema scolastico

# pesi degli archi
beta11 = 1.0

alpha12 = 0.7
alpha10 = 0.2 # ipotizzo una frazione di ripetenti
alpha23 = 0.8
alpha20 = 0.15 # ipotizzo una frazione di ripetenti
alpha30 = 0.99 # fuoricorso

# per k = 0 ipotizzo che il corso sia di nuova istituzione x0 = [0,0,0]
x0 = [0,0,0]

# matrici
A = [
	[1 - alpha12 - alpha10, 0 , 0],
	[alpha12, 1 - alpha23 - alpha20, 0],
	[0, alpha23, 1 - alpha30]
]

B = [
	[beta11],
    [0],
    [0]
]

C = [1, 1, 1]

D = [0]

# creazione modello discreto lineare a coeff. costanti
corso_di_laurea = signal.dlti(A, B, C, D)

# orizzonte temporale, multiplo intero di accademici
t0 = 0
tf = 10
incr = 1
t = np.arange(t0, tf, incr, dtype=int)

# numero di ammissioni per anno accademico costante u(k) = u = 300
u = 300 * np.ones((len(t), 1))

# simulazione modello tempo discreto
_, y, x = signal.dlsim(corso_di_laurea, u, t, x0)

# grafico della successione dell'uscita
plt.stem(t, y)
plt.grid(True)