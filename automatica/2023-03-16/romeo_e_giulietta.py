import numpy as np
from scipy import signal

import matplotlib.pyplot as plt

# modello di romeo e giulietta

# 
a = 0.6
b = a

# matrici A, B, C, D
A = [
     [0, -a],
     [b, 0]
]
B = [
     [0],
     [0]
]
C = np.identity(2)

D = [
     [0],
     [0]
]

# prezzo iniziale
x0 = [0, 10]

# sistema dinamico
ziti = signal.lti(A, B, C, D)

# orizzonte temporale
t = np.linspace(0, 20, 200)

# ingresso
u = np.zeros((len(t), 1))

# simulazione
_, _, x = signal.lsim(ziti, u, t, x0)

# graphs
plt.figure(1)
plt.plot(t, x[:,0], t, x[:,1])
plt.grid(True)

# traiettoria
plt.figure(2)
plt.plot(x[:,0], x[:,1])
plt.grid(True)
