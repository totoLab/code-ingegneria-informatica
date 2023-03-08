#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Mar  8 11:40:37 2023

@author: toto
"""

import numpy as np
from scipy import signal

import matplotlib.pyplot as plt

# modello gerarchico e verifica numerica del Principio di Peter

## pesi degli archi del grafo di transizione

### ingressi
beta11 = 0.6
beta12 = 0.4

### stati
alpha12 = 0.1
alpha13 = 0.05
alpha23 = 0.15
alpha24 = 0.1
alpha34 = 0.1
alpha35 = 0.05
alpha45 = 0.2
alpha46 = 0.15
alpha50 = 0.05
alpha56 = 0.1
alpha60 = 0.45

B = [
    [beta11], [beta12], [0], [0], [0], [0]     
]

A = [
     [-(alpha12 + alpha13), 0, 0, 0, 0, 0],
     [alpha12, -(alpha23 + alpha24), 0, 0, 0, 0],
     [alpha13, alpha23, -(alpha34 + alpha35), 0, 0, 0],
     [0, alpha24, alpha34, -(alpha45 + alpha46), 0, 0],
     [0, 0, alpha35, alpha45, -(alpha56 + alpha50), 0],
     [0, 0, 0, alpha46, alpha56, -alpha60]
]

C = np.identity(6)
D = np.zeros((6, 1))

# orizzonte temporale
t0 = 0
tf = 40
N = 200
t = np.linspace(t0, tf, N)

# stato iniziale
x0 = [300, 100, 150, 50, 90, 30]

# ingresso
u = 10 * np.ones((len(t), 1))

# modello dinamico
azienda = signal.lti(A, B, C, D)

_, _, x = signal.lsim(azienda, u, t, x0)

# frazione di competenti per ciacun livello stipendiale, la divisione è element-wise -> output è un vettore
f1 = x[:,1] / (x[:,0] + x[:,1])
f2 = x[:,3] / (x[:,2] + x[:,3])
f3 = x[:,5] / (x[:,4] + x[:,5])

# grafico delle frazioni
plt.plot(t, f1, t, f2, t, f3)
plt.legend(["I liv", "II liv", "III liv"])
plt.grid(True)


