import numpy as np
from scipy import signal

import matplotlib.pyplot as plt

# modello del mercato a ragnatela

# modello della domanda
a = 0.7
D0 = 80.0

# modello dell'offerta
b = 0.5
Q0 = -10.0

# matrici A, B, C, D
A = [-b/a]
B = [1/a]
C = [1]
D = [0]

# prezzo iniziale
x0 = 100

# sistema dinamico

dim_prezzo = signal.dlti(A, B, C, D)

# orizzonte temporale
t = np.arange(30)

# ingresso
u_ampiezza = D0 - Q0

u = u_ampiezza * np.ones((len(t), 1))

# simulazione

_, _, x = signal.dlsim(dim_prezzo, u, t, x0)

# prezzo equilibrio
p_eq = (D0 - Q0)/(a + b)

p_asintoto = p_eq * np.ones((len(t), 1))

# graphs

plt.plot(t, x, '*', t, p_asintoto)
plt.grid(True)

