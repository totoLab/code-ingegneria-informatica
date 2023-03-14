import numpy as np
from scipy import signal
import matplotlib.pyplot as plt

A = [
 [0.5, 0.5, 0],
 [0.5, 0.25, 0.5],
 [0, 0.25, 0.5]
]

B = [[0], [0], [0]]
C = [0.8, 0.2, 0.2]
D = 0

# condizione iniziale (distribuzione di probabilità di un dato evento)
x0 = [1, 0, 0] # evento certo soleggiato

# modello a tempo discreto
previsioni = signal.dlti(A, B, C, D)

# tempi
t0 = 0
tf = 31
incr = 1 # anno
t = np.arange(t0, tf, incr, dtype=int)

# ingressi
u = np.zeros((len(t), 1)) # il sistema resta isolato anche con u != 0

# simulazione
_, y_, x = singal.dlsim(previsioni, u, t, x0)
  
# graphs
## soleggiato
plt.subplot(3, 1, 1)
plt.stem(t, y[:,0])
plt.grid(True)
plt.title('Soleggiato')

## nuvoloso
plt.subplot(3, 1, 2)
plt.stem(t, y[:,1])
plt.grid(True)
plt.title('Nuvoloso')

## piovoso
plt.subplot(3, 1, 3)
plt.stem(t, y[:,2])
plt.grid(True)
plt.title('Piovoso')