import numpy as np
from scipy.integrate import odeint

import matplotlib.pyplot as plt
import math

# params
l = 6
g = 9.81
m = 30
b = 4

# orizzonte temporale
t = np.arange(0, 80, 1e-3)

# forza esterna
def u(t):
    return 0 # oscillazione libera

# secondo membro
def f(x, t):
    dx_dt = [0, 0]
    dx_dt[0] = x[1]
    dx_dt[1] = - (b/(m * l)) * (2 / math.pi) * np.arctan(10 * x[1]) \
        - (g/l) * np.sin(x[0]) + 1/(m * l) * u(t)
    return dx_dt

# stato iniziale
y0 = [20 * math.pi/180, 0]

# integro eq. differenziale
s = odeint(f, y0, t)

# rappresento la legge oraria (o movimento del sistema, in questo caso è libero)
plt.subplot(2, 1, 1)
plt.plot(t, s[:,0]) # angolo
plt.grid(True)
plt.ylabel('Angolo')

plt.subplot(2, 1, 2)
plt.plot(t, s[:,1]) # velocità angolare
plt.grid(True)
plt.ylabel('Vel. angolare')
plt.xlabel('sec.')

# traiettoria
plt.figure(2)
plt.plot(s[:,0], s[:,1])
plt.grid(True)
plt.title('Traiettoria')