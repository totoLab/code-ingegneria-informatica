import numpy as np
from scipy import signal

import matplotlib.pyplot as plt

# modello del serbatoio senza perdita

# acquisizione e prelievo da fonti esterne
beta11 = 1.0
beta22 = 0.3

alpha12 = 1.0

# matrici A, B, C, D
A = [
  [-alpha12, 0],
  [alpha12, 0]
]

B = [
  [beta11, 0],
  [0, -beta22]
]

C = np.identity(2)

D = np.zeros((2, 2))

#rappresentazione del sistema in una struttura dati
due_serbatoi = signal.StateSpace(A, B, C, D)

x0 = [10.0, 20.0]

# orizzonte temporale
t0 = 0
tf = 10
N = 200
t = np.linspace(t0, tf, N)

# generiamo l'ingresso
u1 = np.ones((len(t), 1))
u2 = np.ones((len(t), 1))

u = np.hstack((u1, u2)) # genera una matrice n x 2

# simuliamo il sistema dinamico
_, _, x = signal.lsim(due_serbatoi, u, t, x0)

plt.subplot(2, 1, 1)
plt.plot(t, x[:,0], 'b-', linewidth=1.5)
plt.grid(True)
plt.subplot(2, 1, 2)
plt.plot(t, x[:,1], 'b-', linewidth=1.5)
plt.grid(True)

# ---------
# Traiettoria del sistema

plt.figure(2)
plt.plot(x[:,0])
plt.xlabel("Serbatoio 1")
plt.ylabel("Serbatoio 2")
plt.grid(True)
