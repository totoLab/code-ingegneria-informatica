import numpy as np # vettori
from scipy import signal # sistemi dinamici
import matplotlib.pyplot as plt # graphs

# variabili del modello
alpha10 = 4.0
beta11 = 2.0

# scrittura dei coefficienti a, b, c, d
A, B = [-alpha10], [beta11]

# osservazione del solo stato
C, D = [1.0], [0]

# immagazzino il sistema dinamico in una struttura dati
# per rappresentare lo stato

serbatoio = signal.StateSpace(A, B, C, D)

# scegliamo un tempo di campionamento uniforme (distanza tra ogni istante che consideriamo uguale)
t0, tf = 0, 2
N = 20 # numero elementi del vettore dei tempi
t = np.linspace(t0, t2, N)

# condizione iniziale
x0 = 0 # serbatoio vuoto

# profilo temporale dell'ingresso (costante per ipotesi)
u = np.ones(len(t)) # una colonna di 1 di lunghezza N

# linear simulation (integrazione numerica)
_, _, x = signal.lsim(serbatoio, u, t, x0)

plt.plot(t, x, 'b-', linewidth=0.5)
plot.grid(True)