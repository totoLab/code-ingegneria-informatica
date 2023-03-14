import numpy as np
from scipy import signal
import matplotlib.pyplot as plt

A = [
 [0, 1.0],
 [1.0, 1.0] 
]

B = [[0], [0]]
C = [1.0, 1.0]
D = 0

# condizione iniziale
x0 = [0, 1]

# modello a tempo discreto
conigli = signal.dlti(A, B, C, D)

# tempi
t0 = 0
tf = 8
incr = 1 # anno
t = np.arange(t0, tf, incr, dtype=int)

# ingressi
u = np.zeros((len(t), 1)) # il sistema resta isolato anche con u != 0

# simulazione
_, y_, x = singal.dlsim(conigli, u, t, x0)

# calcolo frazione di giovani e adulti
giovani = x[:,0] / (x[:,0] + x[:,1])
adulti =  x[:,1] / (x[:,0] + x[:,1])

# determino gamma(k) = y(k+1)/y(k)
gamma = np.zeros(len(t) - 1)

# calcolo di gamma
i = 0
while (i < len(t) - 1):
  gamma[i] = y[i+1]/y[i]
  i += 1
  
# graphs
## frazione di giovani e adulti
plt.figure(1)
plt.stem(t, giovani)
plt.grid(True)
plt.title('Giovani')

plt.figure(2)
plt.stem(t, adulti)
plt.grid(True)
plt.title('Adulti')

## rapporto tra gli anni
plt.figure(3)
plt.stem(t[0:len(t) - 1], gamma)
plt.grid(True)
plt.title('Gamma')
