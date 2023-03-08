import numpy as np
from scipy.integrate import odeint

import matplotlib.pyplot as plt

# coefficienti di flusso
alpha10 = 4.0
beta11 = 2.0

# orizzonte temporale
t0, tf, N = 0, 2, 80
t = np.linspace(t0, tf, N)

# definisco una funzione che rappresenta temporalmente l'ingresso
def u(t):
  if t > 0.25 and t <= 0.75:
    return 1
  return 0
  
# definisco il secondo membro dell'equazione differenziale
# come una funzione di stato e ingresso: x_punto = f(x, u)

def f(x, t):
  dx_dt = [0]
  dx_dt[0] = -alpha10 * x[0] + beta11 * u(t)
  return dx_dt

# soluzione equazione differenziale
stato = odeint(f, y0=0, t=t)

# graph
plt.plot(t, stato[:,0], 'b-', linewidth=0.5)
plt.grid(True)